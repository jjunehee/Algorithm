import java.util.*;

class Solution {
    
    // 지도
    static char[][] map;
    static int xSize;
    static int ySize;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        // 지도 사이즈 계산
        xSize = storage.length; // 세로 크기
        ySize = storage[0].length(); // 가로 크기
        
        map = new char[xSize+2][ySize+2];
        for(int i=1; i<=xSize; i++) {
            String str = storage[i-1];
            for(int j=1; j<=ySize; j++) {
                map[i][j] = str.charAt(j-1);
            }
        }
        
        // 외부 공기 주입
        initialMap();
        
        for(String request : requests) {
            if(request.length() == 1) {
                pickBygigae(request.charAt(0));    
            } else {
                pickByCrain(request.charAt(0));
            }
        }
        
        answer = calculateAnswer();
        
        return answer;
    }
    
    public void pickBygigae(char request) {
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visited = new boolean[xSize+2][ySize+2];
        q.add(new Pos(0,0));
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(map[now.x][now.y] == request) {
                map[now.x][now.y] = '0';
                continue;
            }
            
            for(int dir=0; dir<4; dir++) {
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];                
                if(isBound(nx,ny) || visited[nx][ny]) {
                    continue;
                }
                
                if(map[nx][ny] == '0' || map[nx][ny] == request) {
                    q.add(new Pos(nx,ny));
                    visited[nx][ny] = true;
                }
            }
        }
        
    }
    
    public void pickByCrain(char request) {
        for(int i=0; i<=xSize+1; i++) {
            for(int j=0; j<=ySize+1; j++) {
                if(map[i][j] == request) {
                    map[i][j] = '0';
                }
            }
        }
    }
    
    public boolean isBound(int nx, int ny) {
        if(nx < 0 || nx > xSize + 1 || ny < 0 || ny > ySize + 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public void initialMap() {
        for(int i=0; i<=xSize+1; i++) {
            for(int j=0; j<=ySize+1; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = '0';
                }
            }
        }
    }
    
    public int calculateAnswer() {
        int answer = 0;
        for(int i=0; i<=xSize+1; i++) {
            for(int j=0; j<=ySize+1; j++) {
                if(map[i][j] != '0') {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    public static class Pos {
        int x,y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}