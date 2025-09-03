package org.example.coding.challenges;

public class FrogJmp {
    public static void main(String[] args) {
        int x = 1, y = 10, d = 2;

        int noOfJumps = (int) Math.ceil((double) (y - x) / d);
        System.out.println(noOfJumps);
    }
}
