# comments in a Makefile start with sharp 

# target all means all targets currently defined in this file
all: zip par1 par2 par3
	
seq: sequential
	gcc sequential.c -o sequential -lpthread
	./sequential
	
threads: createThreads
	gcc createThreads.c -o createThreads -lpthread
	./createThreads
	
par1: parallel1.c
	gcc -std=c99 -o parallel1 parallel1.c -lpthread
	./parallel1
	
par2: parallel2.c
	gcc -std=c99 -o parallel2 parallel2.c -lpthread
	./parallel2
	
par3: parallel3.c
	gcc -std=c99 -o parallel3 parallel3.c -lpthread
	./parallel3

# this target is the .zip file that must be submitted to Carmen
zip: Makefile sequential.c parallel1.c parallel2.c parallel3.c createThreads.c 

	zip lab3.zip Makefile sequential.c parallel1.c parallel2.c parallel3.c createThreads.c 

# this target deletes all files produced from the Makefile
# so that a completely new compile of all items is required
clean:
	rm -rf createThreads sequential lab3.zip parallel1 parallel2 parallel3
