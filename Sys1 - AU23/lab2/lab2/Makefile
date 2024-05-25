# comments in a Makefile start with sharp 

# target all means all targets currently defined in this file
all: lab2.zip bit_encode1 bit_encode2

# this target is the .zip file that must be submitted to Carmen
lab2.zip: Makefile bit_encode.c
	zip lab2.zip Makefile bit_encode.c

# this target is the bit cipher executable that requires redirected stdin
bit_encode1: bit_encode1.o
	gcc bit_encode1.o -o bit_encode1

# this target is the dependency for bit_encode1
bit_encode1.o: bit_encode.c
	gcc -std=c99 -pedantic -g -c -o bit_encode1.o bit_encode.c

# this target is the bit cipher executable that prompts for input from the keyboard
bit_encode2: bit_encode2.o
	gcc bit_encode2.o -o bit_encode2

# this target is the dependency for bit_encode2
bit_encode2.o: bit_encode.c
	gcc -std=c99 -pedantic -D PROMPT -g -c -o bit_encode2.o bit_encode.c

# this target deletes all files produced from the Makefile
# so that a completely new compile of all items is required
clean:
	rm -rf *.o bit_encode1 bit_encode2 lab2.zip
