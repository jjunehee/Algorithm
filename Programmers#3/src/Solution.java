import java.util.*;

class Solution {
    static int N,M;
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    public int solution(int[][] maps) {
        
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];
        
        return BFS(maps);
    }
    public int BFS(int[][] maps) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0,0,1));
        
        int nx,ny;
        while(!q.isEmpty()) {
            Pos cur = q.poll();
            if(cur.x == N-1 && cur.y == M-1) {
                return cur.cnt;
            }
            for(int dir=0; dir<4; dir++) {
                nx = cur.x + dx[dir];
                ny = cur.y + dy[dir];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M 
                   || maps[nx][ny]==0|| visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new Pos(nx,ny,cur.cnt+1));
            }
        }
        return -1;
    }
    
    public class Pos {
        int x,y;
        int cnt;
        public Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}

