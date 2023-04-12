import java.util.*;

class Solution {
    static int result = 30000;
    
    public int solution(int[] picks, String[] minerals) {
        
        mine(picks, 0, 5, minerals, 0, 0);
        
        
        return result;
    }
    
    public void mine(int[] picks, int nowpick, int pickCnt, String[] minerals, int cnt, int tired) {
        //모든 광물 채굴 or 모든 곡괭이 사용
        if(cnt == minerals.length || (picks[0]==0 && picks[1]==0 && picks[2]==0 && pickCnt==5)) {
            result = Math.min(result, tired);
            return;
        }
        
        //곡괭이 다 썼을때
        if(pickCnt == 5) {
            if(picks[0] > 0) {
                picks[0]--;
                mine(picks, 0, 1, minerals, cnt+1, tired+1);
                picks[0]++;
            }
            if(picks[1] > 0) {
                picks[1]--;
                if(minerals[cnt].equals("diamond")) {
                    mine(picks, 1, 1, minerals, cnt+1, tired+5);
                } else {
                    mine(picks, 1, 1, minerals, cnt+1, tired+1);
                }
                picks[1]++;
            }
            if(picks[2] > 0) {
                picks[2]--;
                if(minerals[cnt].equals("diamond")) {
                    mine(picks, 2, 1, minerals, cnt+1, tired+25);
                } else if(minerals[cnt].equals("iron")) {
                    mine(picks, 2, 1, minerals, cnt+1, tired+5);
                } else {
                    mine(picks, 2, 1, minerals, cnt+1, tired+1);
                }
                picks[2]++;
            }
        //현재 곡괭이 사용 가능할 때
        } else {
            if(nowpick == 0) {
                mine(picks, nowpick, pickCnt+1, minerals, cnt+1, tired+1);
            }
            if(nowpick == 1) {
                if(minerals[cnt].equals("diamond")) {
                    mine(picks, nowpick, pickCnt+1, minerals, cnt+1, tired+5);
                } else {
                    mine(picks, nowpick, pickCnt+1, minerals, cnt+1, tired+1);
                }
            }
            if(nowpick == 2) {
                if(minerals[cnt].equals("diamond")) {
                    mine(picks, nowpick, pickCnt+1, minerals, cnt+1, tired+25);
                } else if(minerals[cnt].equals("iron")) {
                    mine(picks, nowpick, pickCnt+1, minerals, cnt+1, tired+5);
                } else {
                    mine(picks, nowpick, pickCnt+1, minerals, cnt+1, tired+1);
                }
            }   
        }
    }
}
