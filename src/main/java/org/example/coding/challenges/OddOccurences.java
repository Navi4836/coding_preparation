package org.example.coding.challenges;

import java.util.Arrays;

public class OddOccurences {
    public static void main(String[] args) {
//        int[] A = {1, 3, 5, 3, 1};
        int[] A = {1, 1, 2};
        System.out.println(findOdd(A));
    }

    public static int findOdd(int[] A) {
        Arrays.sort(A);
        for (int i = 0; i < A.length - 2; i = i + 2) {
            if (A[i] < A[i + 1]) {
                return A[i];
            }
        }
        return A[A.length - 1];
    }
}
