TARGET = lab4

all: $(TARGET) zip

$(TARGET): lab4main.c lab4.h print_line.c print_a_line.c process_data.c script.c cal_final.c cum_all.c cum_calculate.c delete_node.c edit_score.c ID_isduplicate.c insert_node.c printHeader.c
	gcc -std=c99 -pedantic *.c lab4.h -o $(TARGET)

zip: lab4main.c lab4.h
	zip lab4.zip *.c lab4.h  $(TARGET)

clean:
	rm -rf *.zip $(TARGET)

