package org.example.coding.challenges;

public class BinaryPeriodOfANumber {
    int solution(int n) {
        int[] d = new int[30];
        int l = 0;
        int p;
        while (n > 0) {
            d[l] = n % 2;
            n /= 2;
            l++;
        }
        for (p = 1; p < 1 + l; ++p) {
            int i;
            boolean ok = true;
            for (i = 0; i < l - p; ++i) {
                if (d[l - i - 1] != d[l - i - 1 - p]) {
                    ok = false;
                    break;
                }
            }
            if (ok && p <= Math.floor((double) l / 2)) {
                return p;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinaryPeriodOfANumber binaryPeriodOfANumber = new BinaryPeriodOfANumber();
        System.out.println(binaryPeriodOfANumber.solution(10));
    }
}
