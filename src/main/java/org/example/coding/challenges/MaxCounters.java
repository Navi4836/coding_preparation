package org.example.coding.challenges;

import java.util.*;

public class MaxCounters {
    public static void main(String[] args) {
        int[] A = {3, 4, 4, 6, 1, 4, 4};
        int N = 5;

        int[] counters = new int[N];
        int maxValue = 0;
        int minValue = 0;
        for (int i = 0; i < A.length; i++) {
            int counter = A[i];
            if (counter <= N) {
                counter--;
                counters[counter] = Math.max(counters[counter] + 1, minValue + 1);
                maxValue = Math.max(counters[counter], maxValue);
                System.out.println(maxValue);
                System.out.println(Arrays.toString(counters));
            }
            if (counter == N + 1) {
                minValue = maxValue;
                System.out.println("min : " + minValue);
            }
        }
        for (int i = 0; i < counters.length; i++) {
            counters[i] = Math.max(minValue, counters[i]);
        }
        System.out.println(Arrays.toString(counters));
        System.out.println(Integer.MAX_VALUE);
    }
}
