class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int one = 0;
        boolean hasZero = false;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1') {
                one++;
            } else {
                answer[1]++;
                hasZero = true;
            }
        }

        while(one != 1 || hasZero) {
            answer[0]++;
            int len = one;
            hasZero = false;
            one = 0;
            
            while(len > 1) {
                int tmp = len % 2;
                len /= 2;
                if(tmp == 1) {
                    one++;
                } else {
                    answer[1]++;
                    hasZero = true;
                }
            }
            
            if(len == 1) {
                one++;
            } else {
                answer[1]++;
            }
            
        }
        
        return answer;
    }
}