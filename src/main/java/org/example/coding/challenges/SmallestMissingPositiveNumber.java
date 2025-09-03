package org.example.coding.challenges;

import java.util.HashSet;
import java.util.Set;

public class SmallestMissingPositiveNumber {
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 5};
        System.out.println(findSmallestMissingNumber(array));
    }

    private static int findSmallestMissingNumber(int[] A) {
        int n = A.length;
        Set<Integer> set = new HashSet<>();
        int smallest = 0;
        for (int num : A) {
            if (num > 0) set.add(num);
        }
        for (int i = 1; i <= n + 1; i++) {
            if (!set.contains(i)) {
                smallest = i;
                break;
            }
        }
        return smallest;
    }
}
