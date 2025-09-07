package org.example.coding.challenges;

import java.util.*;

public class TopKFrequentWords {
    public String[] topKFrequent(String[] nums, int k) {
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(

                (a, b) -> {
                    if (Objects.equals(a.getValue(), b.getValue())) {
                        return a.getKey().compareTo(b.getKey());
                    } else {
                        return b.getValue().compareTo(a.getValue());
                    }
                }

        );
        Map<String, Integer> map = new HashMap<>();

        for (String num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        System.out.println(map);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            System.out.println(pq);
            // do it if you want kth largest (not for top k)
            /*if (pq.size() > k) {
                pq.poll();
            }*/
        }
        System.out.println("pq " + pq);
        String[] res = new String[k];
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
        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();
        String[] array = {"i", "love", "leetcode", "i", "love", "coding"};
        String[] array1 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is", "is"};
        int k = 4;
        topKFrequentWords.topKFrequent(array1, k);
    }
}
