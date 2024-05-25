// deletes one node/student from a linked list given the list head (temp)
#include <stdlib.h>
#include <stdio.h>
#include "lab4.h"

void deleteNode(Node *temp){
	printf("\nPlease enter the student ID  number you wish to delete, followed by enter.\n");
	int id;
	Node* prev = malloc(sizeof(Node));
	prev = temp;
	temp = temp->next;
	scanf(" %i",&id);
	while(temp){
            	// if found
	           	if(temp->student.id == id){
            		printf("\nDo you really want to delete student ID number %i, %s ?\n",temp->student.id, temp->student.name);
            		printf("If yes, enter 1.  If no, enter 2: ");
            		int answer;
            		scanf(" %i",&answer);
            		if(answer==1){
            		printf("student ID number %i, %s  has been deleted.", temp->student.id, temp->student.name);
            		prev->next = temp->next;
            		}
            		break;
            	// if not found
            	}else if(!(temp->next)){
            		printf("\nStudent with ID #%i was not found!",id);
            		break;
            	}else{
            		//temp->student.id != id
            		prev = prev->next;
            		temp = temp->next;
            	}
            }
}
