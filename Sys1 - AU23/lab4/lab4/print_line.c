// code that outputs to the output file directly each student
#include <stdlib.h>
#include <stdio.h>
#include "lab4.h"

void printLine(FILE *out,Node *start){
	fprintf(out, "%s\n%i\n", start->student.name,start->student.id);
	// outputting the score for first category
	if(start->student.cat1.score1>=0){
		fprintf(out,"%g ",start->student.cat1.score1);
	}else{
		fprintf(out,"-1 ");
	}
	if(start->student.cat1.score2>=0){
		fprintf(out,"%g ",start->student.cat1.score2);
	}else{
		fprintf(out,"-1 ");
	}
	if(start->student.cat1.score3>=0){
		fprintf(out,"%g ",start->student.cat1.score3);
	}else{
		fprintf(out,"-1 ");
	}
	fprintf(out,"\n");
	// outputting the score for second category
	if(start->student.cat2.score1>=0){
		fprintf(out,"%g ",start->student.cat2.score1);
	}else{
		fprintf(out,"-1 ");
	}
	if(start->student.cat2.score2>=0){
		fprintf(out,"%g ",start->student.cat2.score2);
	}else{
		fprintf(out,"-1 ");
	}
	if(start->student.cat2.score3>=0){
		fprintf(out,"%g ",start->student.cat2.score3);
	}else{
		fprintf(out,"-1 ");
	}
	fprintf(out,"\n");

	// outputting the score for third category
	if(start->student.cat3.score1>=0){
		fprintf(out,"%g ",start->student.cat3.score1);
	}else{
		fprintf(out,"-1 ");
	}
	if(start->student.cat3.score2>=0){
		fprintf(out,"%g ",start->student.cat3.score2);
	}else{
		fprintf(out,"-1 ");
	}
	if(start->student.cat3.score3>=0){
		fprintf(out,"%g ",start->student.cat3.score3);
	}else{
		fprintf(out,"-1 ");
	}
	fprintf(out,"\n");
	// outputting the score for fourth category
	if(start->student.cat4.score1>=0){
		fprintf(out,"%g ",start->student.cat4.score1);
	}else{
		fprintf(out,"-1 ");
	}
	if(start->student.cat4.score2>=0){
		fprintf(out,"%g ",start->student.cat4.score2);
	}else{
		fprintf(out,"-1 ");
	}
	if(start->student.cat4.score3>=0){
		fprintf(out,"%g ",start->student.cat4.score3);
	}else{
		fprintf(out,"-1 ");
	}
	fprintf(out,"\n");
}
