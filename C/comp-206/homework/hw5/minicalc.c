#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <ctype.h>

int is_anagram(char *s1, char *s2)
{
	int freq1[26] = {0};
	int i;
	for (i = 0; i < strlen(s1); i++) {
		freq1[s1[i] - 'a']++;
	}
	
	for (i = 0; i < strlen(s2); i++) {
		freq1[s2[i] - 'a']--;
	}

	for (i = 0; i < sizeof(freq1)/sizeof(int); i++) {
		if (freq1[i] != 0) {
			return 0;
		}
	}
	return 1;
}

int is_all_lowercase(char *s)
{
	for (int i = 0; i < strlen(s); i++) {
		if (s[i] < 'a' || 'z' < s[i]) {
			return 0;
		}
	}
	return 1;
}

long gcd(long n1, long n2)
{
	if (n1 == 0) return n2;
	return gcd(n2 % n1, n1);
}

long gcd_many(int n, long ns[])
{
	if (n <= 0 ) {
		return 0;
	}
	long gcdAll = ns[0];
	for (int i = 1; i < n; i++) {
		gcdAll = gcd(gcdAll, ns[i]);	
	}
	return gcdAll;
}

int handle_sqrt(int argc, char *argv[])
{
	if (argc != 3) {
		fprintf(stderr, "Invalid number of operands for sqrt.");
		return 2;
	}
	char *num = argv[2];
	char *ptr;

	double answer = strtod(num, &ptr);
	if (*ptr != '\0' || answer <= 0) {
		fprintf(stderr, "Error reading value, not a number.");
		return 3;
	}
	printf("%.2f", sqrt(answer));
	return 0;
}

int handle_anagram(int argc, char *argv[])
{
	if (argc != 4) {
		fprintf(stderr, "Invalid number of operands for anagram");
		return 2;
	}
	
	if (is_all_lowercase(argv[2]) == 0 || is_all_lowercase(argv[3]) == 0) {
		fprintf(stderr, "String is not all lower cased");
		return 3;
	}
	printf(is_anagram(argv[2], argv[3]) ? "true" : "false");
	return 0;
}

int handle_gcd(int argc, char *argv[])
{
	if (argc < 3) {
		fprintf(stderr, "Invalid number of operands for gcd");
		return 2;
	}
	long nums[argc - 2];
      	char *ptr;
	long num;

	for (int i = 2; i < argc; i++) {
		num = strtol(argv[i], &ptr, 10);
		if (*ptr != '\0' || num < 0) {
			fprintf(stderr, "Error reading value, not a number.");
			return 3;
		}
		nums[i-2] = num;
	}
	long gcd = gcd_many(argc-2, nums);
	if (gcd == 0) {
		fprintf(stderr, "Failed gdc.");
		return 3;
	}
	printf("%li", gcd);
	return 0;
}

int main(int argc, char *argv[])
{
	if (argc < 2) {
		fprintf(stderr, "Wrong number of arguments. Use as ./minicalc <function> <function params>.");
		return 1;
	}

     	char *function = argv[1];
	if (strcmp(function, "sqrt") == 0) {
		return handle_sqrt(argc, argv);	
	}
	else if (strcmp(function, "gcd") == 0) {
		return handle_gcd(argc, argv);
	}
	else if (strcmp(function, "anagram") == 0){
		return handle_anagram(argc, argv);
	}
	else {
		fprintf(stderr, "Unknown function name");
		return 1;
	}
}

