import java.util.*;

class Solution {
    static int[][] map = new int[101][101];
    static boolean[][] visited = new boolean[101][101];
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        int N = rectangle.length;
        
        for(int i=0; i<N; i++) {
            int x1 = rectangle[i][0]*2;
            int y1 = rectangle[i][1]*2;
            int x2 = rectangle[i][2]*2;
            int y2 = rectangle[i][3]*2;
            paint(x1,y1,x2,y2);
        }
        
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        answer = search(characterX, characterY, itemX, itemY);
        
        return answer;
    }
    
    public static int search(int sX, int sY, int eX, int eY) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(sX,sY,0));
        
        int nx, ny;
        while(!q.isEmpty()) {
            Pos cur = q.poll();
            if(cur.x == eX && cur.y == eY) {
                return cur.length/2;
            }
            for(int dir=0; dir<4; dir++) {
                nx = cur.x + dx[dir];
                ny = cur.y + dy[dir];
                if(nx>=0 && nx <=100 && ny >=0 && ny <= 100 && map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Pos(nx,ny,cur.length+1));
                }
            }
        }
        return -1;
    }
    
    public static void paint(int x1, int y1, int x2, int y2) {
        
        for(int i=x1; i<=x2; i++) {
            for(int j=y1; j<=y2; j++) {
                if(map[i][j] == 2) {
                    continue;
                }
                map[i][j] = 2;
                if(i==x1 || i == x2 || j==y1 || j==y2) {
                    map[i][j] = 1;
                }
            }
        }
    }
    
    public static class Pos {
        int x, y;
        int length;
        public Pos(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }
}