package org.example.coding.challenges;

public class MaxProductOfThree {

    public int solution(int[] A) {
        int maxWindowProduct = A[0];
        int maxProduct = A[0];
        for(int i = 1; i < A.length - 2; i++) {
            maxWindowProduct = maxWindowProduct * A[i + 1] * A[i + 2];
            maxWindowProduct = Math.max(maxWindowProduct, A[i]);
            maxProduct = Math.max(maxWindowProduct, maxProduct);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        int[] array = {-3, 1, 2, -2, 5, 6};
        MaxProductOfThree maxProductOfThree = new MaxProductOfThree();
        System.out.println(maxProductOfThree.solution(array));
    }
}
