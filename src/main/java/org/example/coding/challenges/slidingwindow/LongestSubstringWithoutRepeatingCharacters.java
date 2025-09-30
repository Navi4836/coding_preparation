package org.example.coding.challenges.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a string s, find the length of the longest substring without duplicate characters.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] c = s.toCharArray();
        int windowLength = 0;
        int pointer = 0;
        int maxLength = 0;
        for (int i = 0; i < c.length; i++) {
            if (map.containsKey(c[i])) {
                while (map.containsKey(c[i])) {
                    map.remove(c[pointer]);
                    pointer++;
                }
            }
            map.put(c[i], i);
            maxLength = Math.max(maxLength, i - pointer + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "adda";
        //String s = "aab";
        //String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
