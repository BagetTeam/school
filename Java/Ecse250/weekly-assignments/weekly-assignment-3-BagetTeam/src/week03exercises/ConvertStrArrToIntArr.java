// the line below defines the package (i.e. folder) where the file lives.
package week03exercises;

public class ConvertStrArrToIntArr {

    public static int[] convertStrArrToIntArr(String[] strArr) {
        // TODO complete method. Hint: use a try-catch block and catch for
        // NumberFormatException and NullPointerException
        if (strArr == null) {
            System.out.println("Null array");
            return null;
        }
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            try {
                intArr[i] = Integer.parseInt(strArr[i].trim());
            } catch (NumberFormatException e) {
                intArr[i] = 0;
                System.out.println("Not a number, setting to 0 at index " + i);
            }
        }

        return intArr;
    }
}