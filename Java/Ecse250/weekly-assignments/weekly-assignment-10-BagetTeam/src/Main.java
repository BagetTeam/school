import java.util.Arrays;

import assignment10.MergeSort;
import assignment10.NumOccurrencesChecker;
import assignment10.QuickSort;

public class Main {

    public static void main(String[] args) throws Exception {
        int[] list = new int[]{3,5,7,2,8,10,1,4};
        int[] list2 = new int[]{2,3,2};

        System.out.println("-------- QUICK SORT --------------");
        System.out.println("\n\n**************** BEFORE SORTING ****************");
        System.out.println(Arrays.toString(list));
        System.out.println("**************** AFTER SORTING ****************");
        QuickSort.quicksort(list, 0, list.length-1);
        System.out.println(Arrays.toString(list));
        System.out.println("***************************************");
        System.out.println();
        System.out.println("\n\n**************** BEFORE SORTING ****************");
        System.out.println(Arrays.toString(list2));
        System.out.println("**************** AFTER SORTING ****************");
        QuickSort.quicksort(list2, 0, list2.length-1);
        System.out.println(Arrays.toString(list2));
        System.out.println("***************************************\n");

        int[] list3 = new int[]{3,5,7,2,8,10,1,4};
        int[] list4 = new int[]{2,3,2};
        System.out.println("-------- MERGE SORT --------------");
        System.out.println("\n\n**************** BEFORE SORTING ****************");
        System.out.println(Arrays.toString(list3));
        System.out.println("**************** AFTER SORTING ****************");
        MergeSort.mergeSort(list3, list3.length);
        System.out.println(Arrays.toString(list3));
        System.out.println("***************************************");
        System.out.println();
        System.out.println("\n\n**************** BEFORE SORTING ****************");
        System.out.println(Arrays.toString(list4));
        System.out.println("**************** AFTER SORTING ****************");
        MergeSort.mergeSort(list4, list4.length);
        System.out.println(Arrays.toString(list4));
        System.out.println("***************************************");

        testNumOccurKTimes( new int[]{2,4,5,5,5,5,5,6,6} , 5,5, true);
        testNumOccurKTimes( new int[]{2,4,5,5,5,5,5,6,6} , 5,4, false);
        testNumOccurKTimes( new int[]{2,4,5,5,5,5,5,6,6} , 5,6, false);
        testNumOccurKTimes( new int[]{438885258} , 438885258,1, true);
        testNumOccurKTimes( new int[]{438885258} , 438885258,2, false);
        testNumOccurKTimes( new int[]{438885258} , 432885258,1, false);
                testNumOccurKTimes( new int[]{1, 2,3,4,5,6,7,8,9,10,11,12} , 5,6, false);

    }

    static void testNumOccurKTimes(int[] list, int n, int k, boolean expectedOutput) {
        NumOccurrencesChecker checker = new NumOccurrencesChecker();
        
        System.out.println("\n\n**************** testNumOccurKTimes ****************");
        System.out.println(Arrays.toString(list));
        System.out.println("***************************************");
        System.out.println("n: "+String.valueOf(n)+", k: "+String.valueOf(k));
        System.out.println("Expected output: "+expectedOutput);
        System.out.println("Actual output: "+checker.numOccursKTimes(list, n, k));
        System.out.println("***************************************");
    }
}
