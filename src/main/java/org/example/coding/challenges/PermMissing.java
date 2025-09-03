package org.example.coding.challenges;

import java.util.*;
import java.util.stream.IntStream;

public class PermMissing {
    public static void main(String[] args) {
        int[] A = {4, 3, 1};
        System.out.println(findMissingPerm(A));
    }

    public static int findMissingPerm(int[] A) {
        Set<Integer> set = new HashSet<>();
        int length = A.length;
        for (int i = 1; i <= length; i++) {
            set.add(i);
        }
        for (int num : A) {
            set.remove(num);
        }
        if (set.isEmpty()) return 1;

        return 0;
    }
}
