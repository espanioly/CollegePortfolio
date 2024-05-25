#include "bounded_buffer.h"
#include <stdio.h>
#include <stdlib.h>

struct bounded_buffer buffer;

void *producerThread(void *num) {
    int *intPtr = (int *)num;
    for (int i = 0; i < MESSAGE_PER_PRODUCER; i++) {
        bounded_buffer_push(&buffer, intPtr);
        printf("\nProduced message: %i", *intPtr);
    }
    return NULL;
}

void *consumerThread(void *num) {
    int *intPtr = (int *)num;
    for (int i = 0; i < MESSAGE_PER_CONSUMER; i++) {
        int *num = (int *)bounded_buffer_pop(&buffer);
        printf("\nConsumer %i consumed message: %i", *intPtr, *num);
        //free(num);
    }
    return NULL;
}

int main() {
    bounded_buffer_init(&buffer, BUFFER_SIZE);

    pthread_t proT[PRODUCER_SIZE];
    pthread_t conT[CONSUMER_SIZE];
    int msg = 10;

    for (int i = 0; i < PRODUCER_SIZE; i++) {
        pthread_create(&proT[i], NULL, producerThread, (void *)&msg);
        msg += MESSAGE_PER_PRODUCER;
    }

    for (int i = 0; i < CONSUMER_SIZE; i++) {
        pthread_create(&conT[i], NULL, consumerThread, (void *)&msg);
        msg *= MESSAGE_PER_CONSUMER;
    }

    for (int i = 0; i < PRODUCER_SIZE; i++) {
        pthread_join(proT[i], NULL);
    }

    for (int i = 0; i < CONSUMER_SIZE; i++) {
        pthread_join(conT[i], NULL);
    }

    bounded_buffer_destroy(&buffer);

    return 0;
}