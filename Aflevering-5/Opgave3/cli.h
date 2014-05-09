#ifndef CUDB_CLI_H
#define CUDB_CLI_H 1

#include <stdlib.h>
#include <stdio.h>

#include "student.h"

/* Functions for interacting with user interactively through console */
extern int cli_get_action(void);
extern int cli_get_name(char *dest, size_t len);
extern int cli_get_start_year(void);
extern int cli_get_start_semester(void);
extern int cli_get_gpa(void);
extern void cli_put_welcome_msg(void);
extern void cli_put_student(student_t *student, int id);

#endif /* cli.h */
