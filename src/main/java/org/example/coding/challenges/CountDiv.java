package org.example.coding.challenges;

public class CountDiv {
    public int solution(int A, int B, int K) {
        int base = (int) Math.ceil((double) A / K);
        base = base * K;
        B = B - base;
        int count = (int) Math.floor((double) B / K);
        count++;
        return count;
    }

    public int solution1(int A, int B, int K) {
        int newB = B - B % K;
        if (A > newB) {
            return 0;
        } else {
            return ((newB - A) / K) + 1;
        }
    }

    public static void main(String[] args) {
        CountDiv cd = new CountDiv();
        System.out.println(cd.solution(6, 11, 2));
        System.out.println(cd.solution1(6, 11, 2));

    }
}
