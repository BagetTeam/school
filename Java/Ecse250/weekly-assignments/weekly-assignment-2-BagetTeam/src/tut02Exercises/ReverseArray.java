// the line below defines the package (i.e. folder) where the file lives.
package tut02Exercises;

public class ReverseArray {

    public static void swap(int[] a, int j, int k) {
        int tmp = a[j];
        a[j] = a[k];
        a[k] = tmp;
    }

    public static void reverseArray(int[] a) {
        // TODO reverse operation occurs in-place so nothing to return. Hint: use the
        // provided swap method.
        int j = 0;
        int k = 0;
        if (a != null) {
            k = a.length - 1;
        }
        while (j < k) {
            swap(a, j, k);
            j++;
            k--;
        }
    }
}
