class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int result = sequence[0];
        int start = 0;
        int end = 0;
        int length = sequence.length+1;
        int rstart = 0;
        int rend = 0;
        while(start < sequence.length && end <= start) {
            if(result < k) {
                start++;
                if(start >= sequence.length) break;
                result += sequence[start];
            } else if(result > k) {
                result -= sequence[end];
                end++;
            } else  {
                if((start - end + 1) < length) {
                    length = start - end + 1;
                    rstart = start;
                    rend = end;
                }
                
                start++;
                if(start < sequence.length) {
                    result += sequence[start];
                }
            }
        }
        
        answer[0] = rend;
        answer[1] = rstart;
        
        return answer;
    }
}