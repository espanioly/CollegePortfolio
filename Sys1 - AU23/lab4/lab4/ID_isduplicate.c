/* This program looks through our linked list and
 * tells the caller program if the studentID # is already
 * in use within the linked list
 */
#include "lab4.h"
int ID_isduplicate(Node *head, int newStudentID){

	Node *traversePtr;

	traversePtr = head;
	while(traversePtr!=NULL){
		if (traversePtr->student.id == newStudentID) {
			/* found it already in the linked list	*/
			printf("\nID already exists!");
			return(1);
		}
		traversePtr = traversePtr->next;
	}
	return(0);
}
