#include <assert.h>
#include <stdio.h>
#include "student.h"
#include "cli.h"

static void add_student(student_list_t *students)
{
	student_t student;

	cli_get_name(student.name, NAME_SIZE);
	student_data_set_year(&student, cli_get_start_year());
	student_data_set_semester(&student, cli_get_start_semester());
	student_data_set_gpa(&student, cli_get_gpa());
	student_list_append(students, &student);
}

static void list_students(student_list_t *students)
{
	int i, gpasum, num_students;
	student_t *sptr;

	printf("\n");

	num_students = student_list_len(students);
	gpasum = 0;
	for (i=0; i < num_students; i++) {
		sptr = student_list_get(students, i);
		gpasum += student_data_get_gpa(sptr);
		cli_put_student(sptr, i);
	}

	if (num_students)
		printf("\nAverage GPA: %.2f\n", gpasum/(double)i);
}

int main(int argc, char **argv)
{
	student_list_t *students;
	int action, error;

	students = student_list_create();

	cli_put_welcome_msg();

	while ((action = cli_get_action())) {
		switch (action) {
		case 1:
			list_students(students);
			break;
		case 2:
			add_student(students);
			break;
		default:
			/* we should never end up here */
			assert(0);
		}
	}

	student_list_destroy(students);
	return 0;
}

