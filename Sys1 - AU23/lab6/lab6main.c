#include <stdio.h>

long sum( long a [], int count);

int main(int argc, char **argv)
{


long a [] = {756, 757, 758, 759, 760, 761, 762, 762, 763, 765};

printf("Main ..... call sum\n");
sum(a, 10);
printf("\ndone!\n");

  return 0;
}
