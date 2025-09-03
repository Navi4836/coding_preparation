package org.example.coding.challenges;

import java.util.EnumSet;

public class TapeEquilibrium {
    public static void main(String[] args) {
        int[] array = {-10, 10};
        int totalSum = 0;
        int leftSum = 0;
        int windowDifference;
        int lowestDifference = Integer.MAX_VALUE;
        for (int number : array) {
            totalSum = totalSum + number;
        }
        System.out.println(totalSum);
        for (int i = 0; i < array.length - 1; i++) {
            leftSum = leftSum + array[i];
           // System.out.println(leftSum);
            windowDifference = Math.abs(totalSum - leftSum * 2);
            lowestDifference = Math.min(windowDifference, lowestDifference);
            System.out.println(lowestDifference);
        }
        System.out.println(lowestDifference);
    }
}
