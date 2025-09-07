package org.example.cache;

import java.util.HashMap;
import java.util.Map;

public class MinusPLUS {
    public String solution(String S) {
        String[] array = S.split("");
        Map<String, String> map = new HashMap();
        map.put("minus", "-");
        map.put("plus", "+");
        StringBuilder result = new StringBuilder();
        StringBuilder concat = new StringBuilder();
        for(String s: array) {
            concat.append(s);
            if(map.containsKey(concat.toString())) {
                result.append(map.get(concat.toString()));
                concat = new StringBuilder();
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        MinusPLUS minusPlus = new MinusPLUS();
        System.out.println(minusPlus.solution("minusplusminus"));
    }
}

