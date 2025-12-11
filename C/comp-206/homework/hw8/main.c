#include "ringbuffer.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	if (argc != 3) {
		fprintf(stderr, "eror");
		return 1;
	}
	char *ptr;
	long int num = strtol(argv[1], &ptr, 10);
	if (*ptr != '\0') {
		fprintf(stderr, "invalid number");
		return 1;
	}
	if (num == 0) {
		return 0;
	}

	FILE *file = fopen(argv[2], "r");
	if (file == NULL) {
		fprintf(stderr, "no such file");
		return 3;
	}
	
	char line_buffer[LINE_LENGTH+1];
	struct ringbuffer buf = rb_init(num);

	while (fgets(line_buffer, LINE_LENGTH, file) != NULL) {
		rb_push(&buf, line_buffer);
	}

	while (rb_pop(&buf, line_buffer) != 0) {
		printf("%s", line_buffer);		
	}
	
	rb_destroy(&buf);
	fclose(file);
}
