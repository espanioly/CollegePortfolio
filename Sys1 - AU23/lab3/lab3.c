#include <stdio.h>
#include <stdlib.h>
#include "lab3.h"

void populateTitles(char ***titles, int *numTitles) {
    printf("How many library book titles do you plan to enter? ");
    scanf("%d", numTitles);

    // Allocating memory for an array of strings
    *titles = (char **)calloc(*numTitles, sizeof(char *));

    printf("Enter the %i book titles one to a line: ", *numTitles);
    for (int i = 0; i < *numTitles; i++) {
        // Allocate memory for each title
        (*titles)[i] = (char *)calloc(100, sizeof(char)); // assuming a maximum length of 99 characters per title
        scanf(" %99[^\n]", (*titles)[i]);
    }
}

void populateFavorites(int **favorites, int *numTitles, int *numFav) {
    printf("\nOf those %d books, how many do you plan to put on your favorites list? ", *numTitles);
    scanf(" %d", numFav);

    printf("\nEnter the number next to each book title you want on your favorites list: ");
    *favorites = (int *)malloc(*numFav * sizeof(int)); // allocate memory for the favorites list

    // For loop that takes the user input for the favorite book list
    for (int i = 0; i < *numFav; i++) {
        // scan before and after space in case of a misinput
        scanf(" %d", &(*favorites)[i]);
    }
}

void saveToFile(char **titles, int *favorites, int numTitles, int numFav) {
    // Prompt user for the file name
    printf("\nWhat file name do you want to use? ");
    char fName[100]; // Assuming a maximum length of 99 characters for the file name
    scanf(" %99s", fName);

    printf("\nYour booklist and favorites have been saved to the file %s.", fName);

    // Open the file for writing
    FILE *out = fopen(fName, "w");

    // Write text to the file
    fprintf(out, "Books I’ve Read:\n");
    for (int i = 0; i < numTitles; i++) {
        fprintf(out, "%d.  %s\n", 1 + i, titles[i]);
    }
    fprintf(out, "\nMy Favorites are:\n");
    for (int i = 0; i < numFav; i++) {
        fprintf(out, "%d. %s\n", i + 1, titles[favorites[i] - 1]);
    }

    fclose(out);
}
int main() {
    char **titles = NULL;
    int *favorites = NULL;
    int numTitles = 0;
    int numFav = 0;

    populateTitles(&titles, &numTitles);

    printf("You’ve entered:\n");
    for (int i = 0; i < numTitles; i++) {
        printf("%d.  %s\n", 1 + i, titles[i]);
    }

    populateFavorites(&favorites, &numTitles, &numFav);

    printf("\nThe books on your favorites list are:\n");
    for (int i = 0; i < numFav; i++) {
        printf("%d. %s\n", i + 1, titles[favorites[i] - 1]);
    }

    printf("Do you want to save them (1=yes, 2=no): ");
    int ans;
    scanf("%d", &ans);

    if (ans == 1) {
        saveToFile(titles, favorites, numTitles, numFav);
    } else {
        printf("\nNamasti.\n");
    }

    // Free allocated memory
    for (int i = 0; i < numTitles; i++) {
        free(titles[i]);
    }
    free(titles);
    free(favorites);

    return 0;
}

