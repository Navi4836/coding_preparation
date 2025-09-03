package org.example.coding.challenges;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LongestConsecutiveZeros {
    public static void main(String[] args) {
        int n = 1041;
        String binary = Integer.toBinaryString(n);
        System.out.println(binary);
        long countOfOne = Arrays.stream(binary.split("")).filter(c -> c.equals("1")).count();
        System.out.println(countOfOne);
        if(countOfOne < 2) {
            System.out.println(0);
        }
        int globalCounter = 0;
        int gapCounter = 0;
        for(char c: binary.toCharArray()) {
            if(c == '0') {
                gapCounter++;
            } else {
                globalCounter = Math.max(globalCounter, gapCounter);
                gapCounter = 0;
            }
        }
        System.out.println(globalCounter);
    }
}
