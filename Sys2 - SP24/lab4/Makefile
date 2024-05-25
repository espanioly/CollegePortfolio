# comments in a Makefile start with sharp 

# target all means all targets currently defined in this file
all: zip main
	
main: main.c bounded_buffer.c bounded_buffer.h
	gcc main.c bounded_buffer.c bounded_buffer.h -o lab4 -lpthread
	./lab4

# this target is the .zip file that must be submitted to Carmen
zip: Makefile main.c bounded_buffer.c bounded_buffer.h 

	zip lab4.zip Makefile main.c bounded_buffer.c bounded_buffer.h 

# this target deletes all files produced from the Makefile
# so that a completely new compile of all items is required
clean:
	rm -rf lab4 lab4.zip 
