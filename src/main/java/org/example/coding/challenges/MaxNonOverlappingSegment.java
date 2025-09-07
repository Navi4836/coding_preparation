package org.example.coding.challenges;

import java.util.Arrays;
import java.util.Comparator;

public class MaxNonOverlappingSegment {

    //refer - https://www.geeksforgeeks.org/dsa/activity-selection-problem-greedy-algo-1/
    // Array sort O(
    public int solution(int[] start, int[] end) {
        int n = start.length;
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            intervals[i][0] = start[i];
            intervals[i][1] = end[i];
        }
        // sort by end time
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));
        int count = 0;
        int finish = -1;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] > finish) {
                count++;
                finish = intervals[i][1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MaxNonOverlappingSegment maxNonOverlappingSegment = new MaxNonOverlappingSegment();
        int[] start = {1, 3, 7, 9, 9};
        int[] end = {5, 6, 8, 9, 10};
        System.out.println(maxNonOverlappingSegment.solution(start, end));
    }
}
