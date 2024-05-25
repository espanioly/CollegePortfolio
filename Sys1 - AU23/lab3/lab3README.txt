BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE PERFORMED ALL OF THE WORK TO CREATE THIS FILE AND/OR DETERMINE THE ANSWERS FOUND WITHIN THIS FILE MYSELF WITH NO ASSISTANCE FROM ANY PERSON (OTHER THAN THE INSTRUCTOR OR GRADERS OF THIS COURSE) AND I HAVE STRICTLY ADHERED TO THE TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
THIS IS THE README FILE FOR LAB 3.

        ◦ Sam Espanioly
Time:5 hours+
Concerns: I was struggling with the lab3.h because I wasn't sure what to put there
debugging description: 
I used ChatGPT to help me catch a few bugs in my code where I was scanning (scanf("\n")) a new line where it shouldn't be which messed up my output in many lines but it spotted it right away.
I used GDP debugger to figure out if the location of the books and titles and favorite titles are being stored in the correct place.
I needed more debugging to do in the output file making sure it uses the right pointers and outputs the right titles.
I also used GPT to help me with the creation of the Makefile.

citation 
prompt: how to create a makefile from terminal
output:
nano Makefile

CC = gcc
CFLAGS = -Wall -Wextra -std=c11
SRC = main.c
OBJ = $(SRC:.c=.o)
TARGET = myprogram

all: $(TARGET)

$(TARGET): $(OBJ)
    $(CC) $(CFLAGS) -o $@ $^

%.o: %.c
    $(CC) $(CFLAGS) -c -o $@ $<

clean:
    rm -f $(OBJ) $(TARGET)

