import java.util.*;

class Solution {
    static int M,N;
    static char[][] map;
    static boolean[][] check;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        M = m;
        N = n;
        
        map = new char[m][n];
        check = new boolean[m][n];
        
        // map 입력
        for(int i=0; i<m; i++) {
            String str = board[i];
            for(int j=0; j<n; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        
        while(true) {
            // 빙고 체크 초기화
            initialCheckMap();

            // 2x2 블록 찾기
            int bingoCnt = findBingoBlock();
            if(bingoCnt == 0) {
                break;
            }
            bombBingo();
            
            answer += bingoCnt;
        }
        return answer;
    }
    
    public static int findBingoBlock() {
        int bingoCnt = 0;
        for(int i=0; i<M-1; i++) {
            for(int j=0; j<N-1; j++) {
                if(isBingo(i,j)) {
                    bingoCnt += checkBingo(i,j);
                }
            }
        }
        return bingoCnt;
    }
    
    public static boolean isBingo(int i, int j) {
        char value = map[i][j];
        if(value == 'X') {
            return false;
        }
        
        if(map[i+1][j] != value) return false;
        if(map[i][j+1] != value) return false;
        if(map[i+1][j+1] != value) return false;
        
        return true;
    }
    
    public static int checkBingo(int i, int j) {
        int cnt = 0;
        
        if(!check[i][j]) {
            check[i][j] = true;
            cnt++;
        }
        if(!check[i+1][j]) {
            check[i+1][j] = true;
            cnt++;
        }
        if(!check[i][j+1]) {
            check[i][j+1] = true;    
            cnt++;
        }
        if(!check[i+1][j+1]) {
            check[i+1][j+1] = true;    
            cnt++;
        }
        return cnt;
    }
    
    public static void bombBingo() {
        
        Queue<Character> q = new LinkedList<>();
        for(int j=N-1; j>=0; j--) {
            for(int i=M-1; i>=0; i--) {
                if(!check[i][j]) {
                    q.add(map[i][j]);
                }
            }
            for(int i=M-1; i>=0; i--) {
                if(!q.isEmpty()) {
                    map[i][j] = q.poll();
                } else {
                    map[i][j] = 'X';
                }
            }
        }
        
    }
    
    public static void initialCheckMap() {
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                check[i][j] = false;
            }
        }
    }
}