import java.util.Arrays;

import week03exercises.*;

public class Main {
    public static void main(String[] args) throws Exception {
        testConvertStrArrToIntArr();
        testStudents();
    }

    public static void testConvertStrArrToIntArr() {
        System.out.println("********************TEST CONVERT-STR-ARR-TO-INT-ARR********************");
        String[] arr = new String[] { "1", "#", " 2 ", " 2.2 ", "23", "2.1" };
        System.out.println("Convert String array " + Arrays.toString(arr) + " to int array: "
                + Arrays.toString(ConvertStrArrToIntArr.convertStrArrToIntArr(arr)));
    }

    public static void testStudents() {
        Student.createStudent("Alex", 12345);
        Student.createStudent("Mary", 45289);
        Student.createStudent("Jenny", 68073);
        Student.createStudent("hi", 1263333);

        System.out.println("********************TEST STUDENTS********************");
        System.out.println("Expected number of students: 4 \nActual number of students: "
                + String.valueOf(Student.getNumStudents()));
    }
}
