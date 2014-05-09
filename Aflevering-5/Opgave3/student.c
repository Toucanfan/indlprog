#include <stdlib.h>
#include "student.h"

static student_list_t *elem_at(student_list_t *head, int index);

student_list_t *student_list_create(void)
{
	student_list_t *head;

	head = malloc(sizeof(student_list_t));
	head->next = NULL;
	head->sptr = NULL;
	return head;
}

int student_list_destroy(student_list_t *head)
{
	student_list_t *elem;
	student_list_t *elem_old;

	if (head->sptr == NULL)
		return 0;

	elem = head;
	while(elem != NULL) {
		free(elem->sptr);
		elem_old = elem;
		elem = elem->next;
		free(elem_old);
	}

	return 0;
}


int student_list_append(student_list_t *head, student_t *student)
{
	student_list_t *elem;
	student_t *sptr;

	/* get last element in list */
	elem = head;
	while (elem->next != NULL)
		elem = elem->next;

	/* append student or assign to first element if new list */
	sptr = malloc(sizeof(student_t));
	*sptr = *student;
	if (elem->sptr == NULL) {
		elem->sptr = sptr;
	} else {
		elem->next = malloc(sizeof(student_list_t));
		elem = elem->next;
		elem->next = NULL;
		elem->sptr = sptr;
	}

	return 0;
}

int student_list_insert(student_list_t *head, student_t *student, int index)
{
	student_list_t *elem;
	student_list_t *elem_old;

	elem = elem_at(head, index);
	if (elem == NULL)
		return -1;

	/* backup current elem */
	elem_old = malloc(sizeof(student_list_t));
	*elem_old = *elem;

	/* make current elem point to new student */
	elem->next = elem_old;
	elem->sptr = malloc(sizeof(student_t));
	*elem->sptr = *student;

	return 0;
}

int student_list_delete(student_list_t *head, int index)
{
	student_list_t *elem;
	student_list_t *elem_next;

	elem = elem_at(head, index);
	if (elem == NULL)
		return -1;

	/* delete elem from list */
	elem_next = elem->next;
	if (elem_next == NULL)
		return -1;
	free(elem->sptr);
	*elem = *elem_next;
	free(elem_next);

	return 0;
}


student_t *student_list_get(student_list_t *head, int index)
{
	student_list_t *elem;

	elem = elem_at(head, index);
	if (elem == NULL)
		return NULL;
	return elem->sptr;
}

size_t student_list_len(student_list_t *head) {
	student_list_t *elem;
	int len;

	if (head->sptr == NULL)
		return 0;

	elem = head;
	len = 0;
	while (elem != NULL) {
		elem = elem->next;
		len++;
	}

	return len;
}

static student_list_t *elem_at(student_list_t *head, int index)
{
	int i;
	student_list_t *elem;

	/* dont accept negative indexes */
	if (index < 0)
		return NULL;

	/* set elem to number `index` in the list */
	elem = head;
	for (i=0; i < index; i++) {
		elem = elem->next;
	}

	return elem;
}

int student_data_get_year(student_t *student)
{
	int year;
	year = student->data & 0x1F; /* clear bits except 0-4 */
	year += 2009;
	return year;
}

int student_data_get_semester(student_t *student)
{
	int sem;
	sem = student->data >> 5; /* bit no. 5 -> bit no. 0 */
	sem &= 0x01; /* clear bits except 0 */
	return sem;
}

int student_data_get_gpa(student_t *student)
{
	int gpa;
	gpa = student->data >> 6; /* bits 6-13 -> bits 0-7 */
	gpa &= 0xFF; /* clear bits except 0-7 */
	return gpa;
}

int student_data_set_year(student_t *student, int year)
{
	year -= 2009;
	year &= 0x1F; /* clear bits except 0-4 */
	student->data &= (~0x1F); /* clear bits 0-4 (year) */
	student->data |= year;
	return 0;
}

int student_data_set_semester(student_t *student, int semester)
{
	student->data &= (~0x20); /* clear bit no. 5 (semester) */
	semester &= 0x01; /* clear bits except 0 */
	semester <<= 5; /* bit no. 0 -> bit no. 5 */
	student->data |= semester;
	return 0;
}

int student_data_set_gpa(student_t *student, int gpa)
{
	student->data &= (~0x3FC0); /* clear bits 6-13 (gpa) */
	gpa &= 0xFF; /* clear bits except 0-7 */
	gpa <<= 6; /* bits 0-7 -> bits 6-13 */
	student->data |= gpa;
	return 0;
}
