import java.util.*;

class Solution {
    int[] percent = {10, 20, 30, 40};
    int[] emoticons, sales, answer;
    int[][] users;
    
    public void setSales(int cnt) {
        if(cnt == emoticons.length) {
            System.out.println();
            check();
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            sales[cnt] = percent[i];
            setSales(cnt+1);
        }
    }
    
    public void check() {
        int[] userPrice = new int[users.length];
        
        for(int i = 0; i < users.length; i++) {
            for(int j = 0; j < sales.length; j++) {
                if(users[i][0] <= sales[j]) {
                    userPrice[i] += (emoticons[j] / 100 * (100 - sales[j]));
                }
            }
        }
        int[] res = new int[2];
        for(int i = 0; i < userPrice.length; i++) {
            if(userPrice[i] >= users[i][1]) {
                res[0]++;
            } else {
                res[1] += userPrice[i];
            }
        }
        if(res[0] > answer[0]) {
            answer = res;
        } else if(res[0] == answer[0]) {
            if(res[1] > answer[1]) {
                answer = res;
            }
        }
    }
    
    public int[] solution(int[][] u, int[] e) {
        emoticons = e;
        users = u;
        sales = new int[emoticons.length];
        answer = new int[2];
        
        
        setSales(0);
        
        
        
        
        
        
        
        
        
        
        
        
        

        return answer;
    }
}