CC = gcc
CFLAGS = -Wall -g

all: cli.o student.o main.o
	$(CC) $(CFLAGS) -o cudb cli.o student.o main.o

student.o: student.h student.c
	$(CC) $(CFLAGS) -c student.c

cli.o: cli.c cli.h
	$(CC) $(CFLAGS) -c cli.c

main.o: main.c cli.h student.h
	$(CC) $(CFLAGS) -c main.c

clean:
	rm -f *.o
	rm -f student_test
	rm -f cli_test
	rm -f cudb
