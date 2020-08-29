package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    void print(String s, int [] a) {
        System.out.print(s);
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println("");
    }

    public int maxSpecialProduct(int[] A) {
//        print("A=",A);
        int [] leftArr = new int[A.length];
        int [] rightArr = new int[A.length];
        leftArr[0] = 0;
        rightArr[A.length - 1] = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i-1] > A[i])
                leftArr[i] = i - 1;
            else {
                int j = leftArr[i - 1];
                while (A[j] <= A[i] && j > 0) {
                    j = leftArr[j];
                }
                leftArr[i] = j;
            }
        }
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] < A[i+1])
                rightArr[i] = i + 1;
            else {
                int j = rightArr[i + 1];
                while (A[j] <= A[i] && j < A.length - 1 && j != 0) {
                    j = rightArr[j];
                }
                rightArr[i] = j;
            }
        }
//        print("L=", leftArr);
//        print("R=", rightArr);
        long maxProduct = 0;
        for (int i = 0; i < A.length; i++) {
            long prod = leftArr[i] * (long)rightArr[i];
            if (maxProduct < prod)
                maxProduct = prod;
        }
        return (int)(maxProduct % 1000000007);
    }


    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.maxSpecialProduct(new int[] {5, 2, 4, 6, 1, 8, 9, 7, 10}));
        System.out.println(solution.maxSpecialProduct(new int[] {1}));
        System.out.println(solution.maxSpecialProduct(new int[] {1, 2}));
        System.out.println(solution.maxSpecialProduct(new int[] {1, 2, 3}));
        System.out.println(solution.maxSpecialProduct(new int[] {3, 2, 1}));
        System.out.println(solution.maxSpecialProduct(new int[] {3, 2, 1, 3, 2, 1}));
        System.out.println(solution.maxSpecialProduct(new int[]{ 7, 5, 7, 9, 8, 7 }));
        System.out.println(solution.maxSpecialProduct(new int[]{ 6, 5, 4, 9, 9, 6, 5, 4, 5, 9, 9 }));
        int [] bigTest = new int[100000];
        for (int i = 0; i < bigTest.length; i++) {
            bigTest[i] = i + 1;
        }
        int lastIndex = bigTest.length - 1;
        bigTest[lastIndex - 2] = 100000;
        bigTest[lastIndex - 1] =  99998;
        bigTest[lastIndex] =      99999;
        System.out.println(solution.maxSpecialProduct(bigTest));
        System.out.println(((long)99999 * 99997) % 1000000007);
    }
}
