import java.util.*;

class Solution {
    public static int basicTime, basicFee;
    public static int feeTime, timeFee;
    
    public int[] solution(int[] fees, String[] records) {
        basicTime = fees[0];
        basicFee = fees[1];
        feeTime = fees[2];
        timeFee = fees[3];
        
        Map<String, Node> car = new HashMap<>();
        Map<String, Integer> pay = new HashMap<>();
        for(String record : records) {
            String[] str = record.split(" ");
            
            String type = str[2];
            if(type.equals("IN")) {
                String[] time = str[0].split(":");
                int hour = Integer.parseInt(time[0]);
                int min = Integer.parseInt(time[1]);
                String number = str[1];
                
                car.put(number, new Node(hour, min));
            } else {
                String[] time = str[0].split(":");
                int hour = Integer.parseInt(time[0]);
                int min = Integer.parseInt(time[1]);
                String number = str[1];
                
                Node node = car.get(number);
                System.out.println(number);
                if(pay.containsKey(number)) {
                    int fee = pay.get(number) + getTime(node.hour, node.min, hour, min);
                    pay.put(number, fee);
                } else {
                    pay.put(number, getTime(node.hour, node.min, hour, min));
                }
                car.remove(number);
            }
        }
        
        for(String number : car.keySet()) {
            Node node = car.get(number);
            System.out.println(number);
            if(pay.containsKey(number)) {
                int fee = pay.get(number) + getTime(node.hour, node.min, 23, 59);
                pay.put(number, fee);
            } else {
                pay.put(number, getTime(node.hour, node.min, 23, 59));
            }
        }
        
        PriorityQueue<Fee> pq = new PriorityQueue<>();
        for(String number : pay.keySet()) {
            int time = pay.get(number);
            
            pq.add(new Fee(number, checkFee(time)));
        }
        
        int[] answer = new int[pay.size()];
        int index = 0;
        while(!pq.isEmpty()) {
            answer[index] = pq.poll().fee;
            index++;
        }
        return answer;
    }
    
    public static int getTime(int startHour, int startMin, int endHour, int endMin) {
        int hour = (endHour - startHour);
        int min = (endMin - startMin);
        if(min < 0) {
            hour--;
            min += 60;
        }
        return hour * 60 + min;
    }
    
    public static int checkFee(int time) {
        int fee = basicFee;
        if(time > basicTime) {
            time -= basicTime;
            fee += ((time / feeTime)) * timeFee;
            if(time % feeTime != 0) {
                fee += timeFee;
            }
        }
        
        return fee;
    }
    
    public static class Fee implements Comparable<Fee> {
        int num;
        String number;
        int fee;
        
        public Fee(String number, int fee) {
            this.number = number;
            this.fee = fee;
            this.num = Integer.parseInt(number);
        }
        
        public int compareTo(Fee f) {
            return Integer.compare(this.num, f.num);
        }
    }
    
    public static class Node {
        int hour;
        int min;
        
        public Node(int hour, int min) {
            this.hour = hour;
            this.min = min;
        }
    }
}