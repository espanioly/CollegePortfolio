#ifndef _BOUNDED_BUFFER_H
#define _BOUNDED_BUFFER_H
#include <pthread.h>
#include <semaphore.h>
#include <sys/syscall.h>

/* do not use any global variables in this file */
#define MAX_MESSAGE_SIZE 10
#define BUFFER_SIZE 5
#define CONSUMER_SIZE 2
#define PRODUCER_SIZE 3
#define TOTAL_MESSAGES 30
#define MESSAGE_PER_PRODUCER 10
#define MESSAGE_PER_CONSUMER 15

struct node_t {
    void *item;
    struct node_t *next;
    struct node_t *prev;
};

struct bounded_buffer{
    struct node_t *head;
    struct node_t *tail;
    int maxSize;
    int size;
    sem_t empty;
    sem_t full;
    sem_t mutex;
};

/* do not change the following function definitions */

/* Initialize a buffer; size is the max number of items in the buffer*/
void bounded_buffer_init(struct bounded_buffer *buffer, int size);

/** Add item to the tail of the buffer. If the buffer is full, wait
 * till the buffer is not full. This function should be thread-safe. */
void bounded_buffer_push(struct bounded_buffer *buffer, void *item);

/** Remove an item from the head of the buffer. If the buffer is empty,
 * wait till the buffer is not empty. Return the removed item. 
 * This function should be thread-safe. */
void* bounded_buffer_pop(struct bounded_buffer *buffer);

/* Release the buffer */
void bounded_buffer_destroy(struct bounded_buffer *buffer);

#endif
