#include "stringbuilder.h"
#include <stddef.h>
#include <stdlib.h>
#include <string.h>


struct string_builder sb_init(int capacity) {
	struct string_builder sb = {.capacity = capacity, .size = 0};
	sb.buf = malloc(capacity * sizeof(char));
	return sb;
}

void sb_grow(struct string_builder *sb) {
	int new_capacity = sb->capacity * 2;
	char *new_content = malloc(new_capacity * sizeof(char));
	if (new_content == NULL) return;

	memcpy(new_content, sb->buf, sb->size);

	free(sb->buf);
	sb->capacity = new_capacity;
	sb->buf = new_content;
}

void sb_appendn(struct string_builder *sb, char const *buf, int len) {
	if (buf == NULL || len < 0) {
		return;
	}
	int added_count = strlen(buf) > len ? len : strlen(buf);
	while (sb->size + added_count > sb->capacity) {
		sb_grow(sb);
	}
	memcpy(sb->buf + sb->size, buf, added_count);
	sb->size += added_count;
}

void sb_append(struct string_builder *sb, char const *buf) {
	if (buf == NULL) {
		return;
	}
	int added_count = strlen(buf);
	 
	while (sb->size + added_count > sb->capacity) {
		sb_grow(sb);
	}
	memcpy(sb->buf + sb->size, buf, added_count);
	sb->size += added_count;
}

void sb_copy_to(const struct string_builder *sb, char *dst, int len) {
	if (dst == NULL || len < 0) {
		return;
	}

	if (sb == NULL || sb->buf == NULL || sb->size == 0) {
		if (len > 0) {
			dst[0] = '\0';
		}
		return;
	}

	int copy_count = sb->size;
	if (copy_count >= len) {
		copy_count = len - 1;
	}

	memcpy(dst, sb->buf, copy_count);
	dst[copy_count] = '\0';
}

char *sb_build(struct string_builder const *sb) {
	if (sb == NULL) {
		return NULL;
	}

	char *content = malloc((sb->size + 1) * sizeof(char));
	if (content == NULL) {
		return NULL;
	}
	sb_copy_to(sb, content, sb->size + 1);
	return content;
}	

void sb_destroy(struct string_builder *sb) {
	free(sb->buf);
}

