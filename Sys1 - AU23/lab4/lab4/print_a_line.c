// code to print one student node's data name id and scores including current grade
#include <stdlib.h>
#include <stdio.h>
#include "lab4.h"

void printALine(Node *start){
	printf( "%s	%i		", start->student.name,start->student.id);
	int avg = 0;
	double cum = 0;
	// outputting the score for first category
	if(start->student.cat1.score1>0){
		avg++;
		cum+=start->student.cat1.score1;
		printf("	%.2f",start->student.cat1.score1);
	}else{
		printf( "		n/a");
	}
	if(start->student.cat1.score2>0){
		avg++;
		cum+=start->student.cat1.score2;
		printf("	%.2f",start->student.cat1.score2);
	}else{
		printf( "		n/a");
	}
	if(start->student.cat1.score3>0){
		avg++;
		cum+=start->student.cat1.score3;
		printf("	%.2f",start->student.cat1.score3);
	}else{
		printf( "		n/a");
	}
	if(avg!=0){
		start->student.cat1.cum = cum/avg;
		printf("	%.2f",start->student.cat1.cum);
	}else{
		printf( "		n/a");
		start->student.cat1.cum = 0;
	}
	// outputting the score for second category
	avg = 0;
	cum = 0;
	if(start->student.cat2.score1>0){
		avg++;
		cum+=start->student.cat2.score1;
		printf("	%.2f",start->student.cat2.score1);
	}else{
		printf( "		n/a");
	}
	if(start->student.cat2.score2>0){
		avg++;
		cum+=start->student.cat2.score2;
		printf("	%.2f",start->student.cat2.score2);
	}else{
		printf( "		n/a");
	}
	if(start->student.cat2.score3>0){
		avg++;
		cum+=start->student.cat2.score3;
		printf("	%.2f",start->student.cat2.score3);
	}else{
		printf( "		n/a");
	}
	if(avg!=0){
		start->student.cat2.cum = cum/avg;
		printf("	%.2f",start->student.cat2.cum);
	}else{
		printf( "		n/a");
		start->student.cat2.cum = 0;
	}
	// outputting the score for third category
	avg = 0;
	cum = 0;
	if(start->student.cat3.score1>0){
		avg++;
		cum+=start->student.cat3.score1;
		printf("	%.2f",start->student.cat3.score1);
	}else{
		printf( "		n/a");
	}
	if(start->student.cat3.score2>0){
		avg++;
		cum+=start->student.cat3.score2;
		printf("	%.2f",start->student.cat3.score2);
	}else{
		printf( "		n/a");
	}
	if(start->student.cat3.score3>0){
		avg++;
		cum+=start->student.cat3.score3;
		printf("	%.2f",start->student.cat3.score3);
	}else{
		printf( "		n/a");
	}
	if(avg!=0){
		start->student.cat3.cum = cum/avg;
		printf("	%.2f",start->student.cat3.cum);
	}else{
		printf( "		n/a");
		start->student.cat3.cum = 0;
	}
	// outputting the score for fourth category
	avg = 0;
	cum = 0;
	if(start->student.cat4.score1>0){
		avg++;
		cum+=start->student.cat4.score1;
		printf("	%.2f",start->student.cat4.score1);
	}else{
		printf( "		n/a");
	}
	if(start->student.cat4.score2>0){
		avg++;
		cum+=start->student.cat4.score2;
		printf("	%.2f",start->student.cat4.score2);
	}else{
		printf( "		n/a");
	}
	if(start->student.cat4.score3>0){
		avg++;
		cum+=start->student.cat4.score3;
		printf("	%.2f",start->student.cat4.score3);
	}else{
		printf( "		n/a");
	}
	if(avg!=0){
		start->student.cat4.cum = cum/avg;
		printf("	%.2f",start->student.cat4.cum);
	}else{
		printf( "		n/a");
		start->student.cat4.cum = 0;
	}
	// calculating the current grade
	start->student.Current_Grade = (.15*start->student.cat1.cum)+(.3*start->student.cat2.cum)+(.2*start->student.cat3.cum)+(.35*start->student.cat4.cum);
	printf("	%.2f",start->student.Current_Grade);
}
