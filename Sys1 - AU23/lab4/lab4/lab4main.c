#include <stdlib.h>
#include <stdio.h>
#include "lab4.h"

int main(int argc, char* argv[]) {
    // Check if the correct number of command line arguments is provided
    if (argc != 3) {
        fprintf(stderr, "Usage: %s filename1 filename2\n", argv[0]);
        return 1;
    }

    // Process the records and calculate grades
    Node *head = malloc(sizeof(Node));  // Initialize head to NULL
    head = NULL;
    char **cat = (char **)malloc(4 * sizeof(char *));
    head = processData(argv[1], head, cat);
    // creating start as a temporary pointer that points to head
	Node *start = malloc(sizeof(Node));
	start = head;
	// script for options
	script(start, cat);
	// Open the output file for writing
	FILE* output = fopen(argv[2], "w");
	
	// adding headers to output file
	for (int i = 0; i < 3; i++) {
        fprintf(output, "%s ",cat[i]);
    }
    // adding the last catagory without a space after
    fprintf(output, "%s\n",cat[3]);
	
    // printing all updated nodes into output file
    int len = 0;
    while(start){
    printLine(output, start);
    start = start->next;
    len++;
    }
    printf("A total of %i student records were written to file %s\n",len,argv[2]);
    start = head;
    
    // freeing allocated memory for all the nodes
    while(start->next){
    	free(start);
    	start = start->next;
    }
	
    // Free dynamically allocated memory for categories
    for (int i = 0; i < 4; i++) {
        free(cat[i]);
    }
    free(cat);
    // closing output file
	fclose(output);
    return 0;
}
