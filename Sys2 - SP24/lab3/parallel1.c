#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#define ARRAY_SIZE 1000000
#define THREAD_NO 10

int sum = 0;

//threading
void *sumThread(void *arg) {
    int *val = (int *)arg;

    // sum
    for (int i = 0; i < ARRAY_SIZE/THREAD_NO; i++) {
        sum += val[i];
    }
}

int main(){

    pthread_t p[THREAD_NO];    

    int num[THREAD_NO][ARRAY_SIZE/THREAD_NO];
    
    srand(100);

    //initialize arrays
    for(int i=0; i< THREAD_NO; i++){
        for(int j=0; j< ARRAY_SIZE/THREAD_NO; j++){
            num[i][j] = rand() % 100;
        }
        //create threads
        pthread_create(&p[i], NULL, sumThread, num[i]);
    }

    //join threads
    for(int i=0; i<THREAD_NO; i++){
        pthread_join(p[i], NULL);
    }

    printf("sum = %d\n", sum);
    return 0;
}