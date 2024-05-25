/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <stdio.h>

int main()
{
    
    char keyPattern[8]= "01100110";
    char text[12]="two fat dogs";
    char Cipher[100]={0};
    unsigned char Key = 0; 
    unsigned char ONE = 1;
    unsigned char Zero = 0;
    
    for (int i = 0; i<8;i++){ 
        if(keyPattern[i] == '1'){
            Key = Key | ONE;
        } 
        if(i != 7){
        Key = Key << 1;
        }
    }
    
    printf("Key = %i\n", Key);
    
    for(int i = 0; i<12;i++)
    {
        Cipher[i] = text[i]^Key;
        if(i == 10){
            printf("\n");
        }
        printf("%02x ", Cipher[i]);
    }
    
    return 0;
}
