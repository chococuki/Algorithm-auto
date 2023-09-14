class Solution {
    public long solution(int[] weights) {
        long[] count = new long[1001];
        
        for(int weight : weights) {
            count[weight]++;
        }
        
        
        long answer = 0;
        for(int i = 100; i <= 1000; i++) {
            if(count[i] > 0) {
                if(count[i] >= 2) {
                    answer += (count[i] * (count[i] - 1) / 2);
                }
                for(int j = i + 1; j <= 1000; j++) {
                    if(count[j] > 0) {
                        infor:
                        for(int a = 2; a <= 4; a++) {
                            for(int b = 2; b <= 4; b++) {
                                if(i * a == j * b) {
                                    System.out.println(i+" "+j);
                                    answer += count[i] * count[j];
                                    break infor;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return answer;
    }
}