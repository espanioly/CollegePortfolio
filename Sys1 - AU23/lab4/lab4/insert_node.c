// code to insert one node into the linked list
#include <stdlib.h>
#include <stdio.h>
#include "lab4.h"
#include <string.h>

void insertNode(Node* newNode,Node* start,char** cat){
	Node *traversePtr = malloc(sizeof(Node));
	traversePtr = start;
	char** name = malloc(2 * sizeof(char *));
	printf("Enter the Student's Name: ");
	scanf(" %s %s",name[1],name[0]);
	// Combine the first and last names and copy to newNode.student.name
    snprintf(newNode->student.name, sizeof(newNode->student.name), "%s %s", name[0], name[1]);
    printf("\nEnter Student's ID: ");
    int cid;
    scanf(" %i", &cid);
    // checking for duplicates with that same ID
    while(ID_isduplicate(traversePtr, cid)){
    	// resetting location of traversePtr to point at the beginning of the list every time user inputs new ID
    	traversePtr = start;
    	printf("\nEnter Student's ID: ");
    	scanf(" %i", &cid);
    }
    // adding the id value after checking that there are no duplicates
    newNode->student.id = cid;
    // digesting all scores into all 4 categories
    printf("\nEnter first %s score (use -1 if there is no score): ", cat[0]);
    scanf(" %f",&(newNode->student.cat1.score1));
    printf("\nEnter second %s score (use -1 if there is no score): ", cat[0]);
    scanf(" %f",&(newNode->student.cat1.score2));
    printf("\nEnter third %s score (use -1 if there is no score): ", cat[0]);
    scanf(" %f",&(newNode->student.cat1.score3));
    printf("\nEnter first %s score (use -1 if there is no score): ", cat[1]);
    scanf(" %f",&(newNode->student.cat2.score1));
    printf("\nEnter second %s score (use -1 if there is no score): ", cat[1]);
    scanf(" %f",&(newNode->student.cat2.score2));
    printf("\nEnter third %s score (use -1 if there is no score): ", cat[1]);
    scanf(" %f",&(newNode->student.cat2.score3));
    printf("\nEnter first %s score (use -1 if there is no score): ", cat[2]);
    scanf(" %f",&(newNode->student.cat3.score1));
    printf("\nEnter second %s score (use -1 if there is no score): ", cat[2]);
    scanf(" %f",&(newNode->student.cat3.score2));
    printf("\nEnter third %s score (use -1 if there is no score): ", cat[2]);
    scanf(" %f",&(newNode->student.cat3.score3));
    printf("\nEnter first %s score (use -1 if there is no score): ", cat[3]);
    scanf(" %f",&(newNode->student.cat4.score1));
    printf("\nEnter second %s score (use -1 if there is no score): ", cat[3]);
    scanf(" %f",&(newNode->student.cat4.score2));
    printf("\nEnter third %s score (use -1 if there is no score): ", cat[3]);
    scanf(" %f",&(newNode->student.cat4.score3));
    printf("\n%s, Student ID# %i has been added with the following information:\n",newNode->student.name,newNode->student.id);
    printHeader(cat);
	printALine(newNode);
    newNode->next = start;
	start = newNode;
	
}
