import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] targets) {
        PriorityQueue<Location> pq = new PriorityQueue<>();
        
        for(int[] target : targets) {
            pq.add(new Location(target[0], target[1]));
        }
        
        int minEnd = pq.poll().end;
        int shootCount = 0;
        while(!pq.isEmpty()) {
            Location target = pq.poll();
            
            if(target.start >= minEnd) {
                minEnd = target.end;
                shootCount++;
            }
        }
        
        shootCount++;
        
        return shootCount;
    }
    
    class Location implements Comparable<Location> {
        int start;
        int end;
        
        public Location(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public int compareTo(Location l) {
            if(this.end == l.end) {
                return Integer.compare(l.start, this.start);
            }
            return Integer.compare(this.end, l.end);
        }
        
        public String toString() {
            return "start: "+this.start+", end: "+this.end;
        }
    }
}

