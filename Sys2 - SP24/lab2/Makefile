# comments in a Makefile start with sharp 

# target all means all targets currently defined in this file
all: compile zip

compile: shellA.c
	gcc shellA.c -o lab2

# this target is the .zip file that must be submitted to Carmen
zip: Makefile shellA.c lab2
	zip lab2.zip Makefile shellA.c lab2

# this target deletes all files produced from the Makefile
# so that a completely new compile of all items is required
clean:
	rm -rf lab2 lab2.zip 
