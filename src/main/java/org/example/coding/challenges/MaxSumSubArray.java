package org.example.coding.challenges;

public class MaxSumSubArray {

    public int solution(int[] A) {
        int maximumSum = A[0];
        int maxWindowSum = A[0];
        for (int i = 1; i < A.length; i++) {
            maxWindowSum = Math.max(maxWindowSum + A[i], A[i]);
            maximumSum = Math.max(maximumSum, maxWindowSum);
            System.out.println("Max sum " + maximumSum + ", WindowSum " + maxWindowSum);
        }
        return maximumSum;
    }

    public static void main(String[] args) {
        MaxSumSubArray maxSumSubArray = new MaxSumSubArray();
        int[] array = {-2,1,-3,4,-1,2,1,-5,4};
        // 5, -1, 4, 4
        // 2, 2, 5, 9
        System.out.println(maxSumSubArray.solution(array));
    }
}
