# comments in a Makefile start with sharp 

# target all means all targets currently defined in this file
all: lab1.zip compile

compile: lab1.c
	gcc lab1.c -o lab1

# this target is the .zip file that must be submitted to Carmen
lab1.zip: Makefile lab1.c
	zip lab1.zip Makefile lab1.c lab1

# this target deletes all files produced from the Makefile
# so that a completely new compile of all items is required
clean:
	rm -rf *.o lab1.zip 
