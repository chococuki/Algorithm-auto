class Solution {
    public long solution(int r1, int r2) {
        int[][] betw = new int[r2][2];
        
        for(int i = 0; i < r2; i++) {
            betw[i][1] = (int)Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(i, 2)));
            
            if(i < r1) {
                betw[i][0] = Math.max((int)Math.ceil(Math.sqrt(Math.pow(r1, 2) - Math.pow(i, 2))), 1);
            } else {
                betw[i][0] = 1;
            }
        }
        
        long result = 0;
        for(int i = 0; i < r2; i++) {
            result += Math.max(betw[i][1] - betw[i][0] + 1, 0);
        }
        
        return result * 4;
        
    }
}