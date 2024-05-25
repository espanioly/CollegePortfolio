// code that reads from a file and puts code into list returns the list head while catagories are saved because of the invention of double pointers
#include <stdlib.h>
#include <stdio.h>
#include "lab4.h"
	
	Node* processData(char *file,Node *head,char **cat){
		// opening input file
		FILE* in = fopen(file,"r");
		printf("Reading student information from file \"%s\"", file);
		int length = 0;
		
		// 30 characters per catagory
		for(int i = 0; i<4;i++){
			char *cate = (char *)malloc(30*sizeof(char));
		 	cat[i] = cate;
			fscanf(in, " %s",cate);
			//printf("%s ", cat[i]);
		}
		// eating the extra new line after the categories
		fscanf(in,"\n");
		printf("\n");
		while(!feof(in)){
			Node* temp = malloc(sizeof(Node));
			// Read student's name
        	fscanf(in, " %39[^\n]", temp->student.name);
			// digesting the data into the temporary node
			fscanf(in," %d\n",&(temp->student.id));
			//printf("\n%s\n%d",temp->student.name,temp->student.id);
			fscanf(in," %f",&(temp->student.cat1.score1));
			fscanf(in," %f",&(temp->student.cat1.score2));
			fscanf(in," %f\n",&(temp->student.cat1.score3));
			fscanf(in," %f",&(temp->student.cat2.score1));
			fscanf(in," %f",&(temp->student.cat2.score2));
			fscanf(in," %f\n",&(temp->student.cat2.score3));
			fscanf(in," %f",&(temp->student.cat3.score1));
			fscanf(in," %f",&(temp->student.cat3.score2));
			fscanf(in," %f\n",&(temp->student.cat3.score3));
			fscanf(in," %f",&(temp->student.cat4.score1));
			fscanf(in," %f",&(temp->student.cat4.score2));
			fscanf(in," %f\n",&(temp->student.cat4.score3));
			//printf("\n%.2f %.2f %.2f", temp->student.cat1.score1,temp->student.cat1.score2,temp->student.cat1.score3);
			//printf("\n%.2f %.2f %.2f", temp->student.cat2.score1,temp->student.cat2.score2,temp->student.cat2.score3);
			//printf("\n%.2f %.2f %.2f", temp->student.cat3.score1,temp->student.cat3.score2,temp->student.cat3.score3);
			//printf("\n%.2f %.2f %.2f", temp->student.cat4.score1,temp->student.cat4.score2,temp->student.cat4.score3);
			temp->student.Final_Grade = -1;
			// linking temp node to head creating a linked list
			temp->next = head;
			head = temp;
			length++;
			}
		// closing input file
		printf("\nA total of %i student records were read from the file %s \n", length, file);
		fclose(in);
		return head;
	}
