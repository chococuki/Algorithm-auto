import java.util.*;

class Solution {
    // d l r u
    public static String[] str = {"d", "l", "r", "u"};
    public static int[] dr = {1, 0, 0, -1};
    public static int[] dc = {0, -1, 1, 0};
    public static String[] route;
    public static int N, M, finishR, finishC, routeCount;
    public static boolean finish;
    public static String result;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        route = new String[k];
        N = n;
        M = m;
        finishR = r;
        finishC = c;
        routeCount = k;
        
        result = "impossible";
        
        int minRoute = distance(x, y);
        if(((k - minRoute) % 2 == 0) && k >= minRoute) {
            dfs(x, y, 0);
        }
        
        return result;
    }
    
    public static void dfs(int r, int c, int dept) {
        if(finish) {
            return;
        }
        
        if(distance(r, c) > (routeCount - dept)) {
            return;
        }
        
        if(dept == routeCount) {
            if(r == finishR && c == finishC) {
                saveRoute();
            }
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if(nr > 0 && nr <= N && nc > 0 && nc <= N) {
                route[dept] = str[i];
                dfs(nr, nc, dept + 1);
            }
        }
    }
    
    public static int distance(int r, int c) {
        return (Math.abs(finishR - r) + Math.abs(finishC - c));
    }
    
    public static void saveRoute() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < routeCount; i++) {
            sb.append(route[i]);
        }
        finish = true;
        result = sb.toString();
    }
}