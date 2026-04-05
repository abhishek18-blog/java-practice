package exception;

import java.lang.Exception;

public class MultipleCatch {
    public static void main(String args[]) {

        int i = 0;
        int j = 0;

        String str = null;
        int nums[] = new int[5];

        try {
            // j = 18 / i;
            System.out.print(str.length());

            System.out.println(nums[1]);
            System.out.print(nums[5]);
        } catch (ArithmeticException e) {
            System.out.println("You can't divide by zero");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Stay in your limits");
        } catch (java.lang.Exception e) {
            System.out.print("something went wrong" + e);
        }

        System.out.print("bye");
    }
}
