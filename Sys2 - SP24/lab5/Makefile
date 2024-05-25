# comments in a Makefile start with sharp 

# target all means all targets currently defined in this file
all: lab5 zip

# zip all files
zip: Makefile main.c bounded_buffer.c bounded_buffer.h hashmap.c hashmap.h word_count.c word_count.h
	zip lab5.zip Makefile main.c bounded_buffer.c bounded_buffer.h hashmap.c hashmap.h word_count.c word_count.h
	
# run main with a test case	
main: main.c bounded_buffer.c bounded_buffer.h hashmap.c hashmap.h word_count.c word_count.h
	gcc main.c bounded_buffer.c bounded_buffer.h hashmap.c hashmap.h word_count.c word_count.h -o lab5 -lpthread
	./lab5  4 3 "1" "2 2 2 2 2" "3 3 3 3 1 1 22 2 2 21 " " 87946 8777 1 1 1 2 2 3 3 3 33333 33333 22 2 222 11 1 1"

# generate exec
lab5: main.c bounded_buffer.c bounded_buffer.h hashmap.c hashmap.h word_count.c word_count.h
	gcc -std=c99 -lpthread -lrt -o lab5 main.c bounded_buffer.c bounded_buffer.h hashmap.c hashmap.h word_count.c word_count.h

#check memory leaks
valgrind:
	valgrind --leak-check=full ./lab5 2 2 "AA BBB AAA AA BB BB" "CCC CC AA BBB AAA AA BB BB"
# this target deletes all files produced from the Makefile
# so that a completely new compile of all items is required	
clean:
	rm -rf lab5 lab5.zip 
