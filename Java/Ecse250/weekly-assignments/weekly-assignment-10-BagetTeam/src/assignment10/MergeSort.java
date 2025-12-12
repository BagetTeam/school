package assignment10;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] arr, int n) {
        if (n <= 1) return;

        int mid = (int) (n / 2);
        int[] left = new int[mid];
        int[] right = new int[n-mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < n; i++) {
            right[i-mid] = arr[i];
        }

        mergeSort(left, mid); //left
        mergeSort(right, n-mid); //right

        merge(arr, left, right, mid, n-mid);
    } 

    private static void merge(int[] arr, int[] left, int[] right, int l, int r) {
        int i = 0, j = 0, k = 0;
        System.out.println("-=-=-=-=-=- ARRAY TO CHANGE -=-=-=-=-=-=-=-");
        System.out.println(Arrays.toString(arr));
        while (i < l && j < r) {
            if (left[i] <= right[j]) {
                arr[k] = left[i++];
            }
            else {
                arr[k] = right[j++];
            }
            k++;
        }
        while (i < l) {
            arr[k++] = left[i++];
        }
        while (j < r) {
            arr[k++] = right[j++];
        }
        System.out.println("-=-=-=-=-=- ARRAY AFTER -=-=-=-=-=-=-=-");
        System.out.println(Arrays.toString(arr));
    }
}
