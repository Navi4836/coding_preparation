package org.example.coding.challenges;

import java.util.HashSet;
import java.util.Set;

public class FrogRiverOne {
    public static void main(String[] args) {
        int[] A = {1, 3, 1, 4, 2, 3, 5, 4};
        System.out.println(findPosition(A, 5));
    }

    public static int findPosition(int[] A, int position) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < position + 1; i++) {
            set.add(i);
        }
        for (int i = 0; i < A.length; i++) {
            set.remove(A[i]);
            if (set.isEmpty()) return i;
        }
        return -1;
    }
}
