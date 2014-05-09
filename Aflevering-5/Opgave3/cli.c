#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "cli.h"
#include "student.h"

static int get_int(int *dest);

/* Functions for interacting with user interactively through console */
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

int cli_get_name(char *dest, size_t len)
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
	return 0;
}

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

void cli_put_welcome_msg(void)
{
	printf("\nWelcome to CUDB - The C University Data Base\n");
	printf("\n0: Halt\n1: List all students\n2: Add a new student\n");
}

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

	printf("s%d %s %d %s %d\n", id, student->name, year, sem, gpa);
}

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
