TARGET = lab6

all: $(TARGET) zip

$(TARGET): lab6.s lab6main.c
	gcc lab6.s lab6main.c -no-pie -o lab6

zip: lab6.s lab6main.c lab6
	zip lab6.zip lab6main.c lab6.s lab6 Makefile

clean:
	rm -rf lab6.zip lab6

