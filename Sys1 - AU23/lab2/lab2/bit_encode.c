/******************************************************************************

This code takes an input file encodes it and decodes it in hex using an 8-bit binary key.

*******************************************************************************/
#include <stdio.h>
// author: Sam Espanioly

int main()
{
    char text[201]; // 200 char plus last one being a null so 201
    //char Cipher[100]={0};
    unsigned char Key = 0; 
    unsigned char ONE = 1;
    unsigned char Zero = 0;
    #ifdef PROMPT
    // prompt to enter input
    printf("Enter clear text:");
    #endif
    text[0]=getchar();
    int sizeT=1;
    while(sizeT<=200&&text[sizeT-1]!='\n'){
    	text[sizeT] = getchar();
    	sizeT++;
    } 
    #ifdef PROMPT
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
        // nice format
        printf("%02x ", text[i]);
    }
    printf("\n");
    printf("Enter a 4-bit key to encrypt the data:");
    #endif
    
    // key pattern to encrypt input
    char keyPattern[8];
    
    for(int i = 0; i<8;i++){
    	if(i>3){ //copies the first four bits and repeats it to the next 4 bits in the array
    		keyPattern[i] = keyPattern[i-4];
    	}else{ //gets the first four bits
    		keyPattern[i]=getchar();
    	}
    }

    for (int i = 0; i<8;i++){ 
        if(keyPattern[i] == '1'){
            Key = Key | ONE;
        } 
        if(i != 7){
        Key = Key << 1; //moves the bits 1 to the left
        }
    }
    #ifdef PROMPT
    printf("\nHex Cipher text is:\n");
    #endif
	
    char Cipher[sizeT-1]; // Changing the Cipher array size to save memory
	
    for(int i = 0; i<sizeT-1;i++) // sizeT is null is we iterate until sizeT-1
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
