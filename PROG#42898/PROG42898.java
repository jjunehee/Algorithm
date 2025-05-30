import java.util.*;

class PRO42898 {
    static int[][] map;
    static int N,M;
    static int[] dx = {1,0};
    static int[] dy = {0,1};
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        N = n;
        M = m;
        
        map = new int[N][M];
        
        int puddleAmount = puddles.length;
        for(int i=0; i<puddleAmount; i++) {
            map[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
        }
        
        // answer = searchByBfs(); // 시간초과
        answer = searchByDP();
        
        return answer;
    }
    
    public static int searchByDP() {
        int[][] dp = new int[N][M];
        dp[0][0] = 1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == -1) {
                    continue;
                }
                
                if(i-1 >= 0 && map[i-1][j] != -1) {
                    dp[i][j] += dp[i-1][j];
                }
                if(j-1 >= 0 && map[i][j-1] != -1) {
                    dp[i][j] += dp[i][j-1];
                }
                
                dp[i][j] %= 1000000007;
            }
        }
        
        return dp[N-1][M-1];
        
    }
    
    public static int searchByBfs() {
        
        // 방문체크 변수
        boolean[][] visited = new boolean[N][M];
        // 탐색을 위한 큐 초기화
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0,0));
        visited[0][0] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            // 현재 위치
            Pos now = q.poll();
            if(now.x == N-1 && now.y == M-1) {
                cnt++;
            }
            
            int nx,ny;
            for(int dir=0; dir<2; dir++) {
                nx = now.x + dx[dir];
                ny = now.y + dy[dir];
                
                if(isBound(nx,ny) || map[nx][ny] == -1) {
                    continue;
                }
                
                q.add(new Pos(nx,ny));
            }
        }
        
        return cnt;
        
    }
    
    public static boolean isBound(int nx, int ny) {
        if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void printMap(int[][] map) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}