import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        int[] book_list = new int[1450];
        for(String[] book : book_time) {
            int start = getTime(book[0]);
            int end = getTime(book[1]);
            
            for(int i = start; i < end + 10; i++) {
                book_list[i]++;
            }
        }
        
        for(int i : book_list) {
            answer = Math.max(answer, i);
        }
        
        
        return answer;
    }
    
    public int getTime(String s) {
        StringTokenizer st = new StringTokenizer(s, ":");
        
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        
        return hour * 60 + minute;
    }
}