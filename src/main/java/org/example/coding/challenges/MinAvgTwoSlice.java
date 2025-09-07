package org.example.coding.challenges;

public class MinAvgTwoSlice {
    public int solution(int[] A) {
        /*float minOfSum2 = Integer.MAX_VALUE;
        int min2StartingPosition = -1;
        int min3StartingPosition = -1;
        for (int i = 0; i < A.length - 1; i++) {
            float twoAvg = (A[i] + A[i + 1]) / 2f;
            if (twoAvg < minAvg) {
                minAvg = twoAvg;
                minAvgStartPosition = i;
            }
            if (i < A.length - 2) {
                float threeAvg = (A[i] + A[i + 1] + A[i + 2]) / 3f;
                if (threeAvg < minAvg) {
                    minAvg = threeAvg;
                    minAvgStartPosition = i;
                }
            }
        }
        return minAvgStartPosition;*/
        return 0;
    }

    public static void main(String[] args) {
        MinAvgTwoSlice minAvgTwoSlice = new MinAvgTwoSlice();
        int[] array = {4, 2, 2, 5, 1, 5, 8};
        System.out.println(minAvgTwoSlice.solution(array));
    }
}
