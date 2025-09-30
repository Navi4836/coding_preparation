package org.example.coding.challenges.twopointers;

public class ContainerWithMostWater {


    public static void main(String[] args) {
        int[] a = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 1};
        System.out.println(maxArea(a));
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            int width = right - left;
            maxArea = Math.max(maxArea, minHeight * width);
            if (height[left] > height[right]) right--;
            else left++;
        }
        return maxArea;
    }
}
