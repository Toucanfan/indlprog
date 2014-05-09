#ifndef CUDB_STUDENT_H
#define CUDB_STUDENT_H 1

#include <sys/types.h>

#define NAME_SIZE 5

typedef struct {
	char name[NAME_SIZE];
	int data;
} student_t;

typedef struct student_list_struct student_list_t;

struct student_list_struct {
	student_list_t *next;
	student_t *sptr;
};

/* student list manipulation functions */
extern student_list_t *student_list_create(void);
extern int student_list_destroy(student_list_t *head);

extern int student_list_append(student_list_t *head, student_t *student);
extern int student_list_insert(student_list_t *head, student_t *student, int index);
extern int student_list_delete(student_list_t *head, int index);

extern student_t *student_list_get(student_list_t *head, int index);
extern size_t student_list_len(student_list_t *head);


/* student_t data manipulation functions */
extern int student_data_get_year(student_t *student);
extern int student_data_get_semester(student_t *student);
extern int student_data_get_gpa(student_t *student);
extern int student_data_set_year(student_t *student, int year);
extern int student_data_set_semester(student_t *student, int semester);
extern int student_data_set_gpa(student_t *student, int gpa);

#endif /* student.h */

