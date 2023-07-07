import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        Stack<Node> stack = new Stack();
        stack.add(new Node(0, numbers[0]));
        
        for(int i = 1; i < numbers.length; i++) {

            while(!stack.isEmpty() && stack.peek().number < numbers[i]) {
                answer[stack.pop().index] = numbers[i];
            }
            stack.add(new Node(i, numbers[i]));
        }
        
        while(!stack.isEmpty()) {
            answer[stack.pop().index] = -1;
        }
        
        return answer;
    }
    
    static class Node {
        int index;
        int number;
        
        public Node(int index, int number) {
            this.index = index;
            this.number = number;
        }
    }
}