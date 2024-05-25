#ifndef _WORD_COUNT_H
#define _WORD_COUNT_H

/* struct to send data to mapper/reducers */
struct thread_data {
    char *str;
    int r;
    int m;
};

void word_count(int m, int r, char **text);

/* mapper and reducer functions */
void *mapper(void *ptr);

void *reducer(void *ptr);

#endif
