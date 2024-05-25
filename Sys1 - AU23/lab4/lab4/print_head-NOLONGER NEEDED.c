#include <stdlib.h>
#include <stdio.h>
#include "lab4.h"

void printHead(FILE *out,char **Category_Names)
{
	fprintf(out,"\nStudent Name\t   Student ID#\t\t%s\t\t%s\t\t%s\t\t%s\t\tCurrent Final\n",
	Category_Names[0],Category_Names[1],Category_Names[2],Category_Names[3]);
	fprintf(out,"\t\t  1       2       3       Cum\t   1       2       3     Cum\t   1       2       3       Cum     1       2       3      Cum\tGrade\tGrade\n");

}

