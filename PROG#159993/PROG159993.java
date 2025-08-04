import java.util.*;
import java.io.*;

class Solution {
    
    static char[][] map;
    static int N,M;
    static Pos now;
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    
    public int solution(String[] maps) {
        int answer = 0;
        
        N = maps.length;
        M = maps[0].length();
        
        map = new char[N][M];
        visited = new boolean[N][M];
        
        // map 이차원 배열로 만들기
        Pos lever = null;
        Pos end = null;
        Pos start = null;
        for(int i=0; i<N; i++) {
            String str = maps[i];
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'L') {
                    lever = new Pos(i,j,0);
                } else if(map[i][j] == 'E') {
                    end = new Pos(i,j,0);     
                } else if(map[i][j] == 'S') {
                    start = new Pos(i,j,0);  
                }
            }
        }
        
        // 레버 탐색
        int distLever = search(start,lever);
        if(distLever == -1) {
            return -1;
        }
        // 방문배열 초기화
        initailizeVisited(visited);
        // 출구로 출발
        int distEnd = search(lever,end);
        if(distEnd == -1) {
            return -1;
        }
        
        answer = distLever + distEnd;
        return answer;
    }
    
    public static int search(Pos start, Pos end) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(start.x,start.y,0));
        
        while(!q.isEmpty()) {
            Pos now = q.poll();
            
            if(now.x == end.x && now.y == end.y) {
                return now.time;
            }
            
            int nx,ny;
            for(int dir=0; dir<4; dir++) {
                nx = now.x + dx[dir];
                ny = now.y + dy[dir];
                if(isBound(nx,ny) || visited[nx][ny] || map[nx][ny] == 'X') {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new Pos(nx,ny, now.time+1));
            }
        }
        return -1;
    }
    
    public static void initailizeVisited(boolean[][] visited) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                visited[i][j] = false;
            }
        }
    }
    
    public static class Pos {
        int x,y;
        int time;
        public Pos(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    public static boolean isBound(int nx, int ny) {
        if(nx < 0 || nx >=N || ny < 0 || ny >= M) {
            return true;
        } else {
            return false;
        }
    }
}