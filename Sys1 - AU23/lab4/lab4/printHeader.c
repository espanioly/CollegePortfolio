#include "lab4.h"

void printHeader(char **Category_Names)
{
	printf("\nStudent Name\t   Student ID#\t\t%s\t\t\t\t%s\t\t\t%s\t\t\t%s\t\t\tCurrent\tGrade\tCurrent\tFinal\n",
	Category_Names[0],Category_Names[1],Category_Names[2],Category_Names[3]);
	printf("\t\t\t\t  1       2       3       Cum\t   1       2       3     Cum\t   1       2       3       Cum     1       2       3      Cum\tGrade\tGrade\n");

}

