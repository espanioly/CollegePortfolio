// script i did it here so it's less messy in the main file
#include <stdlib.h>
#include <stdio.h>
#include "lab4.h"
#include <string.h>

void script(Node* start, char **cat){
	Node* temp = malloc(sizeof(Node));
	Node* newNode = malloc(sizeof(Node));
	temp = start;
	printf("\n\nPlease enter an option between 1 and 10:\n");
	printf("1)\tPrint Student Scores by Student ID\n");
	printf("2)\tPrint Student Scores by Last Name\n");
	printf("3)\tPrint Student Scores for All Students\n");
	printf("4)\tRecalculate Student Scores for a Single Student ID\n");
	printf("5)\tRecalculate All Student Scores\n");
	printf("6)\tInsert a score for a specific Student ID\n");
	printf("7)\tCalculate Final Grades\n");
	printf("8)\tAdd a new student\n");
	printf("9)\tDelete a student\n");
	printf("10)\tExit Program (SAVE)\n\n");
	printf("Option: ");
	int option;
	// var for option 1
	int id;
	// var for option 2
	char lname[40];
	int* dynamicArray;
	int i;
	int foundCount = 0;
	scanf(" %i",&option);
	switch (option) {
        case 1:
            // search by student ID
            printf("Enter the Student ID #: ");
            scanf(" %i",&id);
            printf("\nHunting for %i\n\n", id);
            while(temp){
            	// if found
	           	if(temp->student.id == id){
            		printHeader(cat);
            		printALine(temp);
            		break;
            	// if not found
            	}else if(!(temp->next)){
            		printf("\nStudent with ID #%i was not found!",id);
            		break;
            	}else{
            		//temp->student.id != id
            		temp = temp->next;
            	}
            }
            // recursing
            script(start, cat);
            break;
        case 2:
            // search by last name
            printf("Enter the Student's Last Name: ");
			scanf(" %39[^\n]", lname);
			printf("\nHunting for %s\n\n", lname);

			// Search for the input in the list of student names
			while (temp) {
				if (strstr(temp->student.name, lname) != NULL) {
					// printing the students found as we loop
					printf("\n%i)\t%s %i", (foundCount + 1), temp->student.name, temp->student.id);
					id = temp->student.id;
					foundCount++;
					// reallocating array size accordingly
					dynamicArray = realloc(dynamicArray, foundCount * sizeof(int));
					dynamicArray[foundCount - 1] = id;
				}
				temp = temp->next;
			}

			if (foundCount == 0) {
				printf("\nNo student found with the last name '%s'\n", lname);
			} else if (foundCount > 1) {
				printf("\nThere is more than one student by that name.\n");
				printf("\nPlease indicate which student you want (1-%i): ", foundCount);
				scanf(" %i", &i);

				// Validate user input
				if (i < 1 || i > foundCount) {
					printf("Invalid choice. Exiting.\n");
				} else {
					// searching for student using ID
					temp = start;
					while (temp) {
						// if found
						if (temp->student.id == dynamicArray[i - 1]) {
						    printHeader(cat);
						    printALine(temp);
						    break;
						} else {
						    temp = temp->next;
						}
					}
				}
			} else {
				// Handle the case where only one student is found
				printf("\nFound one student with the last name '%s'\n", lname);
				temp = start;
				// repeating option 1
				while (temp) {
					// if found
					if (temp->student.id == id) {
						printHeader(cat);
						printALine(temp);
						break;
					} else {
						temp = temp->next;
					}
				}
			}

			// Free the allocated memory for dynamicArray
			free(dynamicArray);

            // recursing
            script(start, cat);
            break;
        case 3:
            // all students
            temp = start;
            printHeader(cat);
            while(temp){
            	printf("\n");
            	printALine(temp);
            	temp = temp->next;
            }
            // recursing
            script(start, cat);
            break;
        case 4:
            // Code for option 4
            printf("\nWhat is the Student ID for the scores you want to recalculate? \n");
            printf("\tStudent ID:\t");
            scanf(" %i",&id);
            printf("\nHunting for %i\n\n", id);
            while(temp){
            	// if found
	           	if(temp->student.id == id){
            		cum(temp, cat); 
            		break;
            	// if not found
            	}else if(!(temp->next)){
            		printf("\nStudent with ID #%i was not found!",id);
            		break;
            	}else{
            		//temp->student.id != id
            		temp = temp->next;
            	}
            }
            // recursing
            script(start, cat);
            break;
        case 5:
        	temp = start;
        	while(temp){
            cum_all(temp, cat);
            printf("\n");
            temp = temp->next;
            }
            // recursing
            script(start, cat);
            break;
        case 6:
            printf("\nEnter the Student ID #: ");
            scanf(" %i",&id);
            printf("\nHunting for %i\n\n", id);
            while(temp){
            	// if found
	           	if(temp->student.id == id){
            		printf("Insert a Score for %s ?  Enter 1, if yes. Enter 2, if no: ",temp->student.name);
            		scanf(" %i",&i);
            		if(i==1){
            			edit(temp,cat);
            			printf("\nNote:Final Grade have not been recalculated based on the new value entered.");
            			break;	
            		}else{
            			break;
            		}
            	// if not found
            	}else if(!(temp->next)){
            		printf("\nStudent with ID #%i was not found!",id);
            		break;
            	}else{
            		//temp->student.id != id
            		temp = temp->next;
            	}
            }
            
            // recursing
            script(start, cat);
            break;
        case 7:
        	printHeader(cat);
        	while(temp){
        	printFinal(temp);
        	printf("\n");
        	temp = temp->next;
        	}
            // recursing
            script(start, cat);
            break;
        case 8:
        	insertNode(newNode, temp, cat);
            // recursing
            script(start, cat);
            break;
        case 9:
        	deleteNode(temp);
            // recursing
            script(start, cat);
            break;
        default:
        break;
    }
	
}
