#include <stdio.h>
#include <stdlib.h>

// Function to populate the titles array
void populateTitles(char ***titles, int *numTitles);

// Function to populate the favorites array
void populateFavorites(int **favorites, int *numTitles, int *numFav);

// Function to save data to a file
void saveToFile(char **titles, int *favorites, int numTitles, int numFav);

