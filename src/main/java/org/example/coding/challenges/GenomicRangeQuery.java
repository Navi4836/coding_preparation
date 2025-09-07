package org.example.coding.challenges;

import java.util.Arrays;

public class GenomicRangeQuery {

    public int[] solution(String S, int[] P, int[] Q) {
        int[][] counters = new int[3][S.length() + 1];
        int a = 0, c = 0, g = 0;
        for (int i = 0; i < S.length(); i++) {
            String ch = S.substring(i, i + 1);
            switch (ch) {
                case "A" -> a++;
                case "G" -> g++;
                case "C" -> c++;
            }
            counters[0][i] = a;
            counters[1][i] = c;
            counters[2][i] = g;
        }
        Arrays.stream(counters).flatMapToInt(Arrays::stream).forEach(
                System.out::print
        );
        System.out.println("");

        int[] result = new int[P.length];
        for (int i = 0; i < result.length; i++) {
            int startIndex = P[i];
            int endIndex = Q[i] + 1;
            int r = 4;
            for (int j = 0; j < 3; j++) {
                System.out.println("startIndex : " + startIndex + ", endIndex: " + endIndex);
                System.out.println(counters[j][startIndex] + "!=" + counters[j][endIndex]);
                if (counters[j][startIndex] != counters[j][endIndex]) {
                    r = j + 1;
                    System.out.println(r);
                    break;
                }
                System.out.println(Arrays.toString(result));
            }
            result[i] = r;
            System.out.println(Arrays.toString(result));
        }
        return result;
    }

    public static void main(String[] args) {
        GenomicRangeQuery genomicRangeQuery = new GenomicRangeQuery();
        int[] P = {1, 0, 0};
        int[] Q = {1, 0, 0};
        String S = "A";
        int[] solution = genomicRangeQuery.solution(S, P, Q);
        System.out.println(Arrays.toString(solution));
    }
}
