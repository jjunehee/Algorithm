package BOJ1103;

import java.util.*;
import java.io.*;

public class BOJ1103 {
    static List<Integer>[] graphInfo;
    static int[][] map;
    static boolean[] visited;
    static int[][] dp;
    static boolean cycleCheck;
    static int max = Integer.MIN_VALUE;
    static int N,M;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N*M];
        visited[0] = true;
        

        graphInfo = new ArrayList[N*M];
        for(int i=0; i<N*M; i++) {
            graphInfo[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N; i++) {
            String rowStr = br.readLine(); 
            for(int j=0; j<M; j++) {
                map[i][j] = rowStr.charAt(j) - '0';
                if(map[i][j] == 24) {
                    map[i][j] = -1;
                }
            }
        }

        makeGraphInfo(map);
        searchByDFS(0,0);
        if(cycleCheck) {
            System.out.print(-1);
        } else {
            System.out.print(max + 1); // +1 해주는 이유는 구멍 혹은 장외로 빠지는 경우 더해줌
        }
    }

    public static void makeGraphInfo(int[][] map) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == -1) {
                    continue;
                }
                int dist = map[i][j];
                int nx,ny;
                for(int dir=0; dir<4; dir++) {
                    nx = i + dx[dir]*dist;
                    ny = j + dy[dir]*dist;
                    if(isBound(nx, ny)) {
                        continue;
                    }
                    graphInfo[i*M + j].add(nx*M + ny);
                }
            }
        }
    }

    public static void searchByDFS(int now, int cnt) {
        
        if(cycleCheck || dp[now/M][now%M] > cnt) {
            return;
        }

        dp[now/M][now%M] = cnt;
        max = Math.max(max, dp[now/M][now%M]);  

        for(Integer next : graphInfo[now]) {
            if(visited[next]) {
                cycleCheck = true;
                return;
            }

            if(map[next/M][next%M] == -1) {
                continue;
            }
            visited[next] = true;
            searchByDFS(next, cnt+1);
            visited[next] = false;
        }

    }
    
    public static boolean isBound(int nx, int ny) {
        if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
            return true;
        } else {
            return false;
        }
    }
}