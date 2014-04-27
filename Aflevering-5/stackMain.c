#include <stdio.h>
#include "stack.h"

void printStackInfo(stack_t*);

int main() {

	stack_t* testStack = newStack();

	int i;

	printStackInfo(testStack);
	for (i=0; i<9; i++) {
		push(testStack,i);
		printStackInfo(testStack);
	}

	while (!empty(testStack)) {
		pop(testStack);
		printStackInfo(testStack);
	}
	return 0;
}

void printStackInfo(stack_t* stack_p) {
	printf("Capacity: %2d, Size: %2d, contents: [", stack_p->capacity, stack_p->size);
	int i;
	for (i=0; i<stack_p->size; i++) {
		printf("%d", stack_p->array[i]);
		if (i < stack_p->size-1)
			printf(",");
	}
	puts("]");
}
