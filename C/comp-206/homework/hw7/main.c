#include "stringbuilder.h"
#include <stdio.h>


int main(void) {
	struct string_builder sb = sb_init(10);
	char * arr = "Helo ";
	sb_append(&sb, arr);
	printf("%s\n", sb.buf);
	sb_appendn(&sb, arr, 3);
	printf("%s\n", sb.buf);

	char * arr2 = " World";
	sb_append(&sb, arr2);
	
	printf("%s\n", sb.buf);
	
	sb_destroy(&sb);
	return 0;
}
