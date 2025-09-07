package org.example.coding.challenges;

import java.util.PriorityQueue;

public class KthLargetNumberStringArray {
    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> queue = new PriorityQueue<>(
                (o1, o2) -> {
                    if (o1.length() == o2.length()) {
                        return o1.compareTo(o2);
                    }
                    return Integer.compare(o1.length(), o2.length());
                }
        );

        for (String num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        return queue.peek();
    }

    public static void main(String[] args) {
        KthLargetNumberStringArray kthLargest = new KthLargetNumberStringArray();
        String[] array = {"3", "2", "6", "7", "10"};
        System.out.println(kthLargest.kthLargestNumber(array, 1));
    }
}
