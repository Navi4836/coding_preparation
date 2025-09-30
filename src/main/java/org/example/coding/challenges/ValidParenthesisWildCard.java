package org.example.coding.challenges;

public class ValidParenthesisWildCard {
    public boolean validParenthesis(String str) {
        int min = 0, max = 0;
        for (char c : str.toCharArray()) {
            if (c == '{') {
                min++;
                max++;
            } else if (c == '}') {
                if (min > 0) min--;
                max--;
            } else if (c == '*') {
                if (min > 0) min--;
                max++;
            }

            if (max < 0) return false;
        }
        return min == 0;
    }

    public static void main(String[] args) {
        ValidParenthesisWildCard vp = new ValidParenthesisWildCard();
        String str = "{{}}****{";
        System.out.println(vp.validParenthesis(str));
    }
}
