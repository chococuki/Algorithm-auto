class Solution {
    public boolean checkline(char[][] map, char c) {
        // 가로 확인
        boolean line = false;
        for(int i = 0; i < 3; i++) {
            //가로
            if(map[i][0] == c && map[i][1] == c && map[i][2] == c) {
                line = true;
                break;
            }
            //세로
            if(map[0][i] == c && map[1][i] == c && map[2][i] == c) {
                line = true;
                break;
            }
        }
        
        if(!line) {
            //대각선
            if(map[0][0] == c && map[1][1] == c && map[2][2] == c) {
                line = true;
            } else if(map[2][0] == c && map[1][1] == c && map[0][2] == c) {
                line = true;
            }
        }
        
        return line;
    }
    
    public int solution(String[] board) {
        char[][] map = new char[3][3];
        
        for(int i = 0; i < 3; i++) {
            map[i] = board[i].toCharArray();
        }
        
        //만들어 질수 있는지
        boolean can = true;
        
        //ox 갯수 확인
        int countO = 0;
        int countX = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(map[i][j] == 'O') countO++;
                else if(map[i][j] == 'X') countX++;
            }
        }
        if(countX > countO) can = false;
        if(countO > countX + 1) can = false;
        
        // 완성후에도 진행되었는지
        if(can) {
            if(countX == countO) {
                if(countO >= 3) {
                    if(checkline(map, 'O')) can = false;
                }
            } else if(countO > countX) {
                if(countO >= 3) {
                    if(checkline(map, 'X')) can = false;
                }
            }
            
        }
        
        int answer = (can ? 1 : 0);
        return answer;
    }
}