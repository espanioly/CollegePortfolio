#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include "word_count.h"
#include "hashmap.h"
#include "bounded_buffer.h"


struct buffered_queue *queue;
int print_result(char*, int);

int print_result(char *key, int value){
    printf("count of %s = %d\n", key, value);
    return 0;
}

void *mapper(void *arg){
    char *token; //word inside the string
    char *saveptr;
    const char delimiter[2] = " "; // delimiter for strtok_r
    struct thread_data *data = (struct thread_data *)arg;
    token = __strtok_r(data->str, delimiter, &saveptr);
    // if token not empty
    while (token != NULL){
        // compute hash and push into corresponding queue 
        unsigned int id = crc32((unsigned char *)token, strlen(token)) % data->r;
        buffered_queue_push(&queue[id], token);
        token = __strtok_r(NULL, delimiter, &saveptr);
    }
    // Special message is NULL
    for (int i = 0; i < data->r; i++){
        buffered_queue_push(&queue[i], NULL);
    }
}

void *reducer(void *arg){
    struct thread_data *data = (struct thread_data *)arg;
    int count;
    int id = data->r;
    map_t mymap = hashmap_new(); // hashmap -> reducer
    int numMappersDone = 0;
    // Loop until it break
    while(1){
        // pop token from queue
        char *token = buffered_queue_pop(&queue[id]);

        // Check for NULL
        if(token == NULL){
            numMappersDone++;
            //break from loop after all mappers are done
            if(numMappersDone == data->m){
                break; // all mappers done here
            }
            continue; // else continue
        }
        
        // search then put token in the map
        int error = hashmap_get(mymap, token, &count);
        if(error == MAP_OK){
            // if found then increase count
            hashmap_put(mymap, token, count+1);
        }else{
            // not found
            hashmap_put(mymap, token, 1);
        }
    }
    // iterate and print results
    hashmap_iterate(mymap, print_result);
    // free the hashmap
    hashmap_free(mymap);
}

void word_count(int m, int r, char **text){
    // create thread array for both mappers and reducers 
    pthread_t p[m+r];

    // initalize buffer
    queue = malloc(r * sizeof(struct buffered_queue));
    struct thread_data thread_data[m+r];
    for (int i = 0; i < r; i++){
        buffered_queue_init(&queue[i], m);
        thread_data[m+i].m = m;
        thread_data[m+i].r = i;
        // reducer producing first
        pthread_create(&p[m+i], NULL, reducer, &thread_data[m+i]);
    }
    for(int i=0; i<m; i++){
        thread_data[i].str = text[i];
        thread_data[i].r = r;
        // mapper producing second
        pthread_create(&p[i], NULL, mapper, &thread_data[i]);
    }

    // joining all threads on the same array
    for (int i = 0; i < m+r; i++) {
        pthread_join(p[i], NULL);
    }

    // DESTROY!!!
    for (int i = 0; i < r; i++) {
        buffered_queue_destroy(&queue[i]);
    }
    free(queue);
}



