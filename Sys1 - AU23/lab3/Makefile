CC = gcc
CFLAGS = -std=c99
SRC = lab3.c lab3.h
OBJ = $(SRC:.c=.o)
TARGET = lab3
ZIP_FILE = lab3.zip

all: $(TARGET)

$(TARGET): $(OBJ)
	$(CC) $(CFLAGS) -o $@ $^

%.o: %.c
	$(CC) $(CFLAGS) -c -o $@ $<

zip: lab3.c lab3.h Makefile
	zip $(ZIP_FILE) lab3.c lab3.h Makefile

clean:
	rm -f lab3 lab3.o $(ZIP_FILE)


