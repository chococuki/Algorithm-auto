import java.util.*;

class Solution {
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        
        Map<String, Integer> termMap = new LinkedHashMap<>();
        String[] str;
        for(String s : terms){
            str = s.split(" ");
            termMap.put(str[0], Integer.parseInt(str[1]));
        }
        str = today.split("[.]");
        int tyear = Integer.parseInt(str[0]);
        int tmonth = Integer.parseInt(str[1]);
        int tday = Integer.parseInt(str[2]);
        
        List<Integer> list = new ArrayList<>();
        int index = 0;
        for(String privacie : privacies) {
            index++;
            
            String[] pri = privacie.split(" ");
            String[] date = pri[0].split("[.]");
            int term = termMap.get(pri[1]);
            System.out.println(date[0]);
            int pyear = Integer.parseInt(date[0]);
            int pmonth = Integer.parseInt(date[1]);
            int pday = Integer.parseInt(date[2]);
            
            pmonth += term;
            
            pyear += (pmonth/12);
            pmonth %= 12;
            if(pmonth == 0) {
                pyear--;
                pmonth = 12;
            }
            
            boolean can = false;
            if(tyear < pyear) {
                can = true;
            } else if(tyear == pyear) {
                if(tmonth < pmonth) {
                    can = true;
                } else if(tmonth == pmonth) {
                    if(tday < pday) {
                        can = true;
                    }
                }
            }
            
            if(!can) {
                list.add(index);
            }
            
        }
        
        answer = new int[list.size()];
        for(int i=0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        
        return answer;
    }
}