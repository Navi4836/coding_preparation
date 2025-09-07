package org.example.coding.challenges;

import java.util.Stack;

public class Fish {
    public int solution(int[] A, int[] B) {
        int liveFish = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            int fish = A[i];
            int direction = B[i];
            if (direction == 0) {
                boolean survives = true;
                while (!stack.isEmpty()) {
                    int oppFish = stack.pop();
                    if (oppFish > fish) {
                        survives = false;
                        stack.push(oppFish);
                        break;
                    }
                }
                if (survives) {
                    liveFish++;
                }
            } else {
                stack.push(fish);
            }
        }
        return liveFish + stack.size();
    }

    public static void main(String[] args) {
        int[] fishes = {4, 3, 2, 1, 5};
        int[] direction = {0, 1, 0, 0, 0};
        Fish fish = new Fish();
        System.out.println(fish.solution(fishes, direction));
    }
}
