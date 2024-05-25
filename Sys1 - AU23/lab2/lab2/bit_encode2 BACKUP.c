/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <stdio.h>

int main()
{
    char text[201]; // 200 char plus last one being a null so 201
    char Cipher[100]={0};
    unsigned char Key = 0; 
    unsigned char ONE = 1;
    unsigned char Zero = 0;
    // prompt to enter input
    printf("Enter clear text:");

    text[0]=getchar();
    int sizeT=1;
    while(sizeT<=200&&text[sizeT-1]!='\n'){
    	text[sizeT] = getchar();
    	sizeT++;
    } 
    printf("\n");
    printf("Text entered is:%s\n",text);
    
    //text[sizeT]= (char) 0;   //last char in the array +1 is null 
	printf("Hex encoding is:\n");
	//printing it in hex val
	for(int i = 0; i<sizeT-1;i++)
    {
        if(i == 10){
            printf("\n");
        }
        printf("%02x ", text[i]);
    }
    printf("\n");

	// key pattern to encrypt input
    char keyPattern[8];
    printf("Enter a 4-bit key to encrypt the data:");
    for(int i = 0; i<8;i++){
    	if(i>3){ //copies the first four bits and repeats it to the next 4 bits in the array
    		keyPattern[i] = keyPattern[i-4];
    	}else{ //gets the first four bits
    		keyPattern[i]=getchar();
    	}
    }
    printf("\n");
    
    for (int i = 0; i<8;i++){ 
        if(keyPattern[i] == '1'){
            Key = Key | ONE;
        } 
        if(i != 7){
        Key = Key << 1; //moves the bits 1 to the left
        }
    }
    
    printf("Hex Cipher text is:\n");
    for(int i = 0; i<sizeT-1;i++)
    {
    		// cipher at i location = text[i] XOR Key so text or key if both then = zero
        Cipher[i] = text[i]^Key;
        if(i == 10){
            printf("\n");
        }
        printf("%02x ", Cipher[i]);
    }
    printf("\n");
    
    return 0;
}
