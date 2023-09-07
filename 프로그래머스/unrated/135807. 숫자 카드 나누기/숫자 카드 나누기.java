import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int maxA = 0;
        int minA = Integer.MAX_VALUE;
        for(int i : arrayA) {
            maxA = Math.max(maxA, i);
            minA = Math.min(minA, i);
        }
        
        int maxB = 0;
        int minB = Integer.MAX_VALUE;
        for(int i : arrayB) {
            maxB = Math.max(maxB, i);
            minB = Math.min(minB, i);
        }
        
        int maxValue = Math.max(maxA, maxB);
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
            
        for(int i = 2; i <= minA; i++) {
            boolean is = true;
            for(int a : arrayA) {
                if(a % i != 0) {
                    is = false;
                    break;
                }
            }
            if(is) {
                listA.add(i);
            }
        }
        
        for(int i = 2; i <= minB; i++) {
            boolean is = true;
            for(int b : arrayB) {
                if(b % i != 0) {
                    is = false;
                    break;
                }
            }
            if(is) {
                listB.add(i);
            }
        }
        
        int answer = 0;
        for(int i : listA) {
            boolean is = true;
            for(int b : arrayB) {
                if(b % i == 0) {
                    is = false;
                    break;
                }
            }
            if(is) {
                answer = Math.max(answer, i);
            }
        }
        
        for(int i : listB) {
            boolean is = true;
            for(int a : arrayA) {
                if(a % i == 0) {
                    is = false;
                    break;
                }
            }
            if(is) {
                answer = Math.max(answer, i);
            }
        }
        
        return answer;
    }
}