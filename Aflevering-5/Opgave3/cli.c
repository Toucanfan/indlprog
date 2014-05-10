#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "cli.h"
#include "student.h"

/* Functions local to this translation unit (file) */
/* Get integer from console (stdin) and save it in `dest`. Return non-zero on error. */
static int get_int(int *dest)
{
	char *linebuf;
	size_t buflen;
	int r, i;

	r = 0;
	if (getline(&linebuf, &buflen, stdin) < 2)
		r = -1;
	else if (!sscanf(linebuf, "%d", &i))
		r = -1;
	else
		*dest = i;

	free(linebuf);
	return r;
}


/* Functions for interacting with user interactively through console */
/* Promt user for action number and return it */
int cli_get_action(void)
{
	int action, r;
	while (1) {
		printf("\nEnter action:\n");
		r = get_int(&action);
		if ((r < 0) || (action < 0) || (action > 2))
			printf("No such action exist!\n");
		else
			break;
	}
	return action;
}

/* Promt user for name and save it in buffer `dest` which has the size `len` */
void cli_get_name(char *dest, size_t len)
{
	char *linebuf;
	size_t buflen;
	size_t nread;

	buflen = len;
	linebuf = malloc(len);
	while (1) {
		printf("\nEnter name (%d characters only)\n", (int)len-1);
		if ((nread = getline(&linebuf, &buflen, stdin)) > 1) {
			linebuf[nread-1] = '\0'; /* remove \n char */
			linebuf[len-1] = '\0'; /* Prevent buffer overrun */
			strcpy(dest, linebuf);
			break;
		} else {
			printf("You didn't enter anything!\n");
		}
	}
	free(linebuf);
}

/* Promt user for start year and return it */
int cli_get_start_year(void)
{
	int year, r;
	while (1) {
		printf("\nEnter start year (2009-2040):\n");
		r = get_int(&year);
		if ((r < 0) || (year < 2009) || (year > 2040))
			printf("Not in allowed range!\n");
		else
			break;
	}
	return year;
}

/* Promt user for start semester and return it */
int cli_get_start_semester(void)
{
	int sem, r;
	while (1) {
		printf("\nEnter start semester (0=Autumn/1=Spring):\n");
		r = get_int(&sem);
		if ((r < 0) || (sem < 0) || (sem > 1))
			printf("Not an option!\n");
		else
			break;
	}
	return sem;
}

/* Promt user for GPA and return it */
int cli_get_gpa(void)
{
	int gpa, r;
	while (1) {
		printf("\nEnter GPA (0-255)\n");
		r = get_int(&gpa);
		if ((r < 0) || (gpa < 0) || (gpa > 255))
			printf("Not in allowed range!\n");
		else
			break;
	}
	return gpa;
}

/* Print CUDB welcome message */
void cli_put_welcome_msg(void)
{
	printf("\nWelcome to CUDB - The C University Data Base\n");
	printf("\n0: Halt\n1: List all students\n2: Add a new student\n");
}

/* Print student info as well as student id */
void cli_put_student(student_t *student, int id)
{
	int year, gpa, r;
	const char *sem;

	year = student_data_get_year(student);
	gpa = student_data_get_gpa(student);
	r = student_data_get_semester(student);

	if (r)
		sem = "Spring";
	else
		sem = "Autumn";

	printf("s%04d %s %d %s %d\n", id, student->name, year, sem, gpa);
}
