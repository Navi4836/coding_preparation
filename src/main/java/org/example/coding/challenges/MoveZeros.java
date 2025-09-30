package org.example.coding.challenges;

import java.util.Arrays;

public class MoveZeros {
    // Method to move zeros to the end of the array
    public static void moveZerosToEnd(int[] arr) {
        int n = arr.length;
        int nonZeroIndex = 0; // Index to place the non-zero elements
        // Iterate through the array
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                // Swap non-zero element with the element at nonZeroIndex
                int temp = arr[i];
                arr[i] = arr[nonZeroIndex];
                arr[nonZeroIndex] = temp;
                nonZeroIndex++;
            }
        }
    }
    // Main method to test the functionality
    public static void main(String[] args) {
        int[] arr = {0, 1, 9, 0, 3, 12, 0, 5};

        System.out.println("Original array: " + Arrays.toString(arr));

        // Move zeros to the end
        moveZerosToEnd(arr);

        System.out.println("Array after moving zeros: " + Arrays.toString(arr));
    }
}
