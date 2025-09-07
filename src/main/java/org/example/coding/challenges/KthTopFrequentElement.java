package org.example.coding.challenges;

import java.util.*;

public class KthTopFrequentElement {
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder())
                //Comparator.comparingInt(Map.Entry::getValue)
        );
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }

        System.out.println(map);
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            map1.put(i, 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            System.out.println(pq);
            // do it if you want kth largest (not for top k)
            /*if (pq.size() > k) {
                pq.poll();
            }*/
        }
        System.out.println("pq " + pq);
        int[] res = new int[k];
        // this is for top k
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().getKey();
            System.out.println(i);
            System.out.println(Arrays.toString(res));
        }
        //System.out.println(pq.poll());
        //System.out.println("pq " + pq);
        /*System.out.println(pq.poll());
        System.out.println(pq.poll());*/
        return res;
    }

    public static void main(String[] args) {
        KthTopFrequentElement kthElement = new KthTopFrequentElement();
        int[] array = {1, 1, 1, 2, 2, 3, 4, 5};
        int k = 3;
        kthElement.topKFrequent(array, k);
    }
}
