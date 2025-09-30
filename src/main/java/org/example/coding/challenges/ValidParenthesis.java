package org.example.coding.challenges;

import java.util.Stack;

public class ValidParenthesis {
    public int solution(String S) {
        System.out.println(S);
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            stack.search("}");
            if (c == '{') {
                stack.push('}');
            } else if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return 0;
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }

    public static void main(String[] args) {
        ValidParenthesis vp = new ValidParenthesis();
        //String s = "([)()]";
        String s = "{[()()]}";
        System.out.println(vp.solution(s));
    }
}
