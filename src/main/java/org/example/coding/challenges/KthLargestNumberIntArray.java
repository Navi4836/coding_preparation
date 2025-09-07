package org.example.coding.challenges;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestNumberIntArray {
    public int kthLargestNumber(int[] nums, int k) {
        int kthlargest = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int num : nums) {
            queue.offer(num);
            System.out.println(queue);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek().intValue();
    }

    public static void main(String[] args) {
        KthLargestNumberIntArray kthLargest = new KthLargestNumberIntArray();
        int[] array = {3, 2, 6, 7, 10};
        System.out.println(kthLargest.kthLargestNumber(array, 1));
    }
}
