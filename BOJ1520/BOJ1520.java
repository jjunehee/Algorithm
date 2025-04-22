package BOJ1520;

import java.util.*;
import java.io.*;


public class BOJ1520 {

    static int N,M;
    static int[][] map;
    static int[][] dp;

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // input
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        // dp[i][j] : (i,j)에서 N,M까지의 경로의 수
        dp = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        int ret = searchByDfs(0,0);
        System.out.print(ret);

    }

    public static int searchByDfs(int x, int y) {
        if(x == N-1 && y == M-1) {
            return 1;
        }

        if(dp[x][y] != -1) {
            return dp[x][y]; 
        }
        
        dp[x][y] = 0;

        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(isBound(nx, ny)) {
                continue;
            }
            
            if(map[x][y] > map[nx][ny]) {
                dp[x][y] += searchByDfs(nx,ny);
            }
        }

        return dp[x][y];
    }

    public static boolean isBound(int nx, int ny) {
        if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
            return true;
        } else {
            return false;
        }
    }
}