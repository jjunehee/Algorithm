import java.util.*;

class Solution {
    
    static int N;
    static int[][] map; // n*n 지도
    static int[] dx = {1,1,0,-1,-1,-1,0,1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    
    public int[] solution(int n) {
        N = n;
        
        int[] answer = {};
        // 지도를 만든다.
        map = new int[n][n];
        
        moveByBFS();
        
        List<Integer> resultList = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] != 0) {
                    resultList.add(map[i][j]);
                }
            }
        }
        
        answer = new int[resultList.size()];
        int idx = 0;
        for(int num : resultList) {
            answer[idx++] = num;
        }
        
        return answer;
    }
    
    public void moveByBFS() {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0,0,0));
        
        int[] dirChange = new int[4];
        dirChange[0] = 0;
        dirChange[1] = 2;
        dirChange[2] = 3;
        dirChange[3] = 3;
        
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        
        
        int cnt = 1;
        map[0][0] = cnt++;
            
        int dirIdx = 0;
        while(!q.isEmpty()) {
            Pos now = q.poll();
            
            int nx = now.x + dx[now.dir];
            int ny = now.y + dy[now.dir];
            if(isBound(nx,ny) || visited[nx][ny]) {
                dirIdx = (dirIdx % 3) + 1;
                int dirPlus = dirChange[dirIdx];
                now.dir = (now.dir+dirPlus) % 8;
                
                int tx = now.x + dx[now.dir];
                int ty = now.y + dy[now.dir];
                if(isBound(tx,ty) || visited[tx][ty]) {
                    break;
                } else {
                    q.add(new Pos(now.x,now.y,now.dir));
                    continue;    
                }
            }
            q.add(new Pos(nx,ny,now.dir));
            visited[nx][ny] = true;
            map[nx][ny] = cnt++;
            
        }
        
    }
    
    public boolean isBound(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
            return true;
        } else {
            return false;
        }
    }
    
    public void printMap() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(map[i][j] + "         ");
            }
            System.out.println();
        }
    }
    
    public static class Pos {
        int x,y;
        int dir;
        public Pos(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}