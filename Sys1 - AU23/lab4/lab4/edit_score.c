// code to edit the scores of one student
#include <stdlib.h>
#include <stdio.h>
#include "lab4.h"

void edit(Node* temp, char** cat){
	printf("\n Which category?\n1) %s\n2) %s\n3) %s\n4) %s\n",cat[0],cat[1],cat[2],cat[3]);
	int i = 0;
	scanf(" %i",&i);
	int score;
	float newScore;
	// Category
	switch(i){
		case 1:
		printf("Which score?\nEnter 1, 2, or 3:");
		scanf(" %i",&score);
			//score1/2/3
			switch(score){
			case 1:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat1.score1 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			case 2:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat1.score2 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			case 3:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat1.score3 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			default:
			printf("WRONG INPUT");
			break;
			}
		break;
		case 2:
		printf("Which score?\nEnter 1, 2, or 3:");
		scanf(" %i",&score);
			//score1/2/3
			switch(score){
			case 1:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat2.score1 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			case 2:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat2.score2 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			case 3:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat2.score3 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			default:
			printf("WRONG INPUT");
			break;
			}
		break;
		case 3:
			printf("Which score?\nEnter 1, 2, or 3:");
		scanf(" %i",&score);
			//score1/2/3
			switch(score){
			case 1:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat3.score1 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			case 2:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat3.score2 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			case 3:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat3.score3 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			default:
			printf("WRONG INPUT");
			break;
			}
		break;
		case 4:
			printf("Which score?\nEnter 1, 2, or 3:");
		scanf(" %i",&score);
			//score1/2/3
			switch(score){
			case 1:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat4.score1 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			case 2:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat4.score2 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			case 3:
				printf("\nEnter New Score:  ");
				scanf(" %f",&newScore);
				temp->student.cat4.score3 = newScore;
				printHeader(cat);
				printALine(temp);
			break;
			default:
			printf("WRONG INPUT");
			break;
			}
		break;
		default:
		printf("MISS INPUT");
		break;
		}
}
