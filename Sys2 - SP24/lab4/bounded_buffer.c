#include "bounded_buffer.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

/*Do not use any global variables for implementation*/

/* Initialize a buffer; size is the max number of items in the buffer*/
void bounded_buffer_init(struct bounded_buffer *buffer, int maxSize) {
    buffer->head = NULL;
    buffer->tail = NULL;
    buffer->maxSize = maxSize;
    buffer->size = 0;

    sem_init(&buffer->empty, 0, maxSize);
    sem_init(&buffer->full, 0, 0);
    sem_init(&buffer->mutex, 0, 1);
}

void bounded_buffer_push(struct bounded_buffer *buffer, void *item) {
    sem_wait(&buffer->empty);
    sem_wait(&buffer->mutex);

    struct node_t *new_node = (struct node_t *)malloc(sizeof(struct node_t));
    if (new_node == NULL) {
        printf("Error: Memory allocation failed\n");
        sem_post(&buffer->mutex);
        sem_post(&buffer->empty);
        return;
    }
    new_node->item = item;
    new_node->next = NULL;

    if (buffer->tail != NULL) {
        buffer->tail->next = new_node;
    } else {
        buffer->head = new_node;
    }
    buffer->tail = new_node;
    buffer->size++;

    sem_post(&buffer->mutex);
    sem_post(&buffer->full);
}

void* bounded_buffer_pop(struct bounded_buffer *buffer) {
    sem_wait(&buffer->full);
    sem_wait(&buffer->mutex);

    if (buffer->head == NULL) {
        sem_post(&buffer->mutex);
        sem_post(&buffer->full);
        return NULL;
    }

    void *item = buffer->head->item;
    struct node_t *temp = buffer->head;
    buffer->head = buffer->head->next;
    if (buffer->head == NULL) {
        buffer->tail = NULL;
    }
    free(temp);
    buffer->size--;

    sem_post(&buffer->mutex);
    sem_post(&buffer->empty);

    return item;
}

void bounded_buffer_destroy(struct bounded_buffer *buffer) {
    sem_destroy(&buffer->full);
    sem_destroy(&buffer->empty);
    sem_destroy(&buffer->mutex);
}
