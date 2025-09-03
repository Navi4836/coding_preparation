package org.example.coding.challenges;

import java.util.Arrays;

public class RotateArrayRight {

    public static void reverse(int[] array, int i, int j) {
        if (i == j) return;
        while (i < j) {
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
            i++;
            j--;
            System.out.println(Arrays.toString(array));
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int k = 2;
        int length = array.length;
        k = k % length;
        rotateLeft(array, length, k);
        System.out.println(Arrays.toString(array));
    }

    // rotate right
    public static void rotateRight(int[] array, int length, int k) {
        reverse(array, 0, length - 1);
        reverse(array, 0, k - 1);
        reverse(array, k, length - 1);
    }

    // rotate right
    public static void rotateLeft(int[] array, int length, int k) {
        reverse(array, k, length - 1);
        reverse(array, 0, k - 1);
        reverse(array, 0, length - 1);
    }
}
