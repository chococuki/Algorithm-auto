import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> nicknames = new HashMap<>();
        Queue<Node> que = new LinkedList<>();
        
        for(String rec : record) {
            String[] str = rec.split(" ");
            
            String type = str[0];
            if(type.equals("Enter")) {
                nicknames.put(str[1], str[2]);
                que.add(new Node(str[1], "Enter"));
            } else if(type.equals("Leave")) {
                que.add(new Node(str[1], "Leave"));
            } else if(type.equals("Change")) {
                nicknames.put(str[1], str[2]);
            }
        }
        
        int len = que.size();
        String[] answer = new String[len];
        for(int i = 0; i < len; i++) {
            Node node = que.poll();
            
            if(node.type.equals("Enter")) {
                answer[i] = nicknames.get(node.id) + "님이 들어왔습니다.";
            } else {
                answer[i] = nicknames.get(node.id) + "님이 나갔습니다.";
            }
        }
        return answer;
    }
    
    public static class Node {
        private String id;
        private String type;
        
        public Node(String id, String type) {
            this.id = id;
            this.type = type;
        }
    }
}