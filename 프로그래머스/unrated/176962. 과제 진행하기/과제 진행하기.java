import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer;
        
        Stack<HW> stack = new Stack<>();
        PriorityQueue<HW> pq = new PriorityQueue<>();
        for(int i = 0; i < plans.length; i++) {
            String[] str = plans[i][1].split(":");
            int start = Integer.parseInt(str[0])*60 + Integer.parseInt(str[1]);
            pq.add(new HW(plans[i][0], start, Integer.parseInt(plans[i][2])));
        }
        
        int time = 0;
        HW now = null;
        List<String> rs = new ArrayList<>();
        while(!pq.isEmpty() || !stack.isEmpty()) {
            HW tmp = null;
            if(!pq.isEmpty()) {
                tmp = pq.peek();
                if(tmp.startTime > time) {
                    if(!stack.isEmpty()) {
                        now = stack.pop();
                    } else {
                        now = pq.poll();
                        time = now.startTime;
                    }
                } else {
                    now = pq.poll();
                    if(!pq.isEmpty()) tmp = pq.peek();
                    else tmp = null;
                }
            } else {
                now = stack.pop();
            }
            
            if(tmp == null) {
                rs.add(now.name);
            } else {
                if(tmp.startTime >= time + now.playTime) {
                    rs.add(now.name);
                    time += now.playTime;
                } else {
                    now.playTime -= (tmp.startTime - time);
                    stack.add(now);
                    time = tmp.startTime;
                }
            }
            
        }
        
        answer = new String[rs.size()];
        for(int i = 0; i < rs.size(); i++) {
            answer[i] = rs.get(i);
        }
        
        return answer;
    }
}

class HW implements Comparable<HW> {
    String name;
    int startTime;
    int playTime;
    
    public HW(String name, int startTime, int playTime) {
        this.name = name;
        this.startTime = startTime;
        this.playTime = playTime;
    }
    
    public int compareTo(HW o) {
        return this.startTime - o.startTime;
    }
}