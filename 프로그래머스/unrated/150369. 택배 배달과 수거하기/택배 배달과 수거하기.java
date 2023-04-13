import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        long answer = 0;
        int start = n-1;
        for(int i = n-1; i >= 0; i--) {
            if(deliveries[i]!=0 || pickups[i]!=0) {
                start = i;
                break;
            }
        }
        
        int i = start;
        while(i >= 0) {
            if(deliveries[i]==0 && pickups[i]==0) i--;
            if(i < 0) break;
            if(deliveries[i]!=0 || pickups[i]!=0) {
                // System.out.println(Arrays.toString(deliveries));
                // System.out.println(Arrays.toString(pickups));
                // System.out.println();
                
                answer += (i+1)*2;
                
                //deliveries
                int weight = 0;
                for(int j = i; j >= 0; j--) {
                    if(weight + deliveries[j] <= cap) {
                        weight += deliveries[j];
                        deliveries[j] = 0;
                    } else {
                        deliveries[j] -= (cap-weight);
                        break;
                    }
                }

                //pickup
                weight = 0;
                for(int j = i; j >= 0; j--) {
                    if(weight + pickups[j] <= cap) {
                        weight += pickups[j];
                        pickups[j] = 0;
                    } else {
                        pickups[j] -= (cap-weight);
                        break;
                    }
                }
            }
        }
        

        return answer;
    }
}