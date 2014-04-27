#include <stdio.h>
#include <stdlib.h>
#include "stack.h"

#define INITIALSTACKSIZE 1
#define EXPANSIONFACTOR 2

void expandStack(stack_t* stack_p);

stack_t* newStack(void) {
	stack_t* freshStack = (stack_t*) malloc(sizeof(stack_t));
	freshStack->array = (int*) malloc(INITIALSTACKSIZE*sizeof(int));
	freshStack->capacity = INITIALSTACKSIZE;
	freshStack->size = 0;
	return freshStack;
}

void push(stack_t* stack_p, int value) {
	if (stack_p->size == stack_p->capacity)
		expandStack(stack_p);
	stack_p->array[stack_p->size++] = value;
}

void expandStack(stack_t* stack_p) {
	int* tempArray = (int*) malloc(EXPANSIONFACTOR*stack_p->capacity*sizeof(int)); // allocate array of double the prior size
	int i;
	for (i=0; i<stack_p->capacity; i++)
		tempArray[i] = stack_p->array[i]; // put the old values into the new array
	free(stack_p->array);
	stack_p->array = tempArray; // make the stack's array pointer point to the new array
	stack_p->capacity = EXPANSIONFACTOR*stack_p->capacity;
}

int pop(stack_t* stack_p){
	if (stack_p->size<1)
		fputs("Empty stack attempted popped\n",stderr);
	return stack_p->array[--stack_p->size];
}

int top(stack_t* stack_p) {
	if (stack_p->size<1)
			fputs("No top on empty stack\n",stderr);
	return stack_p->array[stack_p->size-1];
}

int empty(stack_t* stack_p) {
	return stack_p->size == 0;
}

