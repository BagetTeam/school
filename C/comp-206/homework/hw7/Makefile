sb_test: main.o stringbuilder.o
	gcc main.o stringbuilder.o -o sb_test

main.o: main.c stringbuilder.h
	gcc -c main.c -Wall -o main.o

stringbuilder.o: stringbuilder.c stringbuilder.h
	gcc -c stringbuilder.c -o stringbuilder.o

clean:
	rm -f *.o sb_test
