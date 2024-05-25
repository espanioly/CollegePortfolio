#include <stdio.h>
#include <stdlib.h>
	
	struct Cat_Grade{
		float score1;
		float score2;
		float score3;
		float cum;
	};
	struct Data {
		char name[40]; 
 		int id;
		struct Cat_Grade cat1;
		struct Cat_Grade cat2;
		struct Cat_Grade cat3;
		struct Cat_Grade cat4;
		float Current_Grade; 
		float Final_Grade;
	};

	typedef struct Node { 
		struct Data student; 
		struct Node *next;
	} Node;
	
		// processes the input file into nodes
		Node* processData(char *file,Node *head,char **cat);
		
		// function to print headers to file -----NOT NEEDED
		//void printHead(FILE *out,char **Category_Names);
		
		//printing header to the screen
		void printHeader(char **Category_Names);
		// function that prints all lines of students
		void printLine(FILE *out,Node *start);
		//function that prints one line for a single student
		void printALine(Node *start);
		// function recalculates everything including final grades
		void printFinal(Node *start);
		// function that prints script and does everything 
		void script(Node* start, char **cat);
		// calculate cumilitive for one student
		void cum(Node* start, char** cat);
		// calculate everything for all students
		void cum_all(Node* start, char** cat);
		// function to edit a certain score in a certain category for a certain node
		void edit(Node* temp, char** cat);
		// checks for duplicates with same student ID
		int ID_isduplicate(Node *head, int newStudentID);
		// function to insert a new student node
		void insertNode(Node* newNode,Node* start,char** cat);
		//deleting a node
		void deleteNode(Node* temp);
