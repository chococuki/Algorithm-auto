import java.util.*;

class Solution {
    public int minCount;
    
    public int solution(String begin, String target, String[] words) {
        boolean targetExist = false;
        
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(target)) {
                targetExist = true;
            }
        }
        
        if(!targetExist) {
            return 0;
        }
        
        Map<String, Boolean> used = new HashMap<>();
        for(String word : words) {
            used.put(word, false);
        }
        
        Map<String, List<String>> changeList = makeWordMap(words);
        
        List<String> list = new ArrayList<>();
        for(String word : words) {
            if(checkWord(begin, word)) {
                list.add(word);
            }
        }
        
        minCount = words.length + 1;
        for(String word : list) {
            used.put(word, true);
            dfs(used, changeList, 1, word, target);
            used.put(word, false);
        }

        return minCount == (words.length + 1) ? 0 : minCount;
    }
    
    public void dfs(Map<String, Boolean> used, Map<String, List<String>> changeList, 
                   int count, String word, String target) {
        if(word.equals(target)) {
            minCount = Math.min(minCount, count);
            return;
        }
        
        if(count > minCount) {
            return;
        }
        
        for(String tmp : changeList.get(word)) {
            if(used.get(tmp)) {
                continue;
            }
            
            used.put(tmp, true);
            dfs(used, changeList, count + 1, tmp, target);
            used.put(tmp, false);
        }
    }
    
    public Map<String, List<String>> makeWordMap(String[] words) {
        Map<String, List<String>> wordMap = new HashMap<>();
        
        for(String begin : words) {
            wordMap.put(begin, new ArrayList<>());
            for(String target : words) {
                if(begin.equals(target)) {
                    continue;
                } else if(checkWord(begin, target)) {
                    wordMap.get(begin).add(target);
                }
            }
        }
        
        return wordMap;
    }
    
    public boolean checkWord(String begin, String target) {
        boolean different = false;
        for(int i = 0; i < begin.length(); i++) {
            if(begin.charAt(i) != target.charAt(i)) {
                if(different) {
                    return false;
                } else {
                    different = true;
                }
            }
        }
        return true;
    }
    
}

class Node implements Comparable<Node> {
    char[] word;
    int count;
    
    public Node(char[] word, int count) {
        this.word = word;
        this.count = count;
    }
    
    public int compareTo(Node n) {
        return Integer.compare(this.count, n.count);
    }
}