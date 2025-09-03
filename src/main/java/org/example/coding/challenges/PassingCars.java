package org.example.coding.challenges;

import com.sun.security.jgss.GSSUtil;

import java.util.Arrays;

public class PassingCars {
    public static void main(String[] args) {
        int[] cars = {1, 0, 1, 1, 1};
        // (0,1), (0, 3), (0, 4), (2, 3), (2, 4)
        int passingCars = 0;
        int eastBoundCarsSeen = 0;
        for (int car : cars) {
            if (car == 0) {
                eastBoundCarsSeen++;
            } else {
                passingCars += eastBoundCarsSeen;
                if (passingCars > 1000000000) System.out.println(-1);
            }
        }
        System.out.println(passingCars);
    }
}
