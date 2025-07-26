import java.io.*;
import java.util.*;

class Solution {
    
    static int N,M;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    
    static char[][] map;
    static boolean[][] visited;
    
    public int solution(String[] board) {
        int answer = 0;
        
        N = board.length;
        M = board[0].length();
        map = new char[N][M];
        visited = new boolean[N][M];
        
        Queue<Pos> q = new LinkedList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R') {
                    q.add(new Pos(i,j,0));
                    visited[i][j] = true;
                }
            }
        }
    
        while(!q.isEmpty()) {
            
            Pos now = q.poll();
            if(map[now.x][now.y] == 'G') {
                answer = now.time;
            }
            
            
            for(int dir=0; dir<4; dir++) {
                int nowX = now.x;
                int nowY = now.y;
                while(true) {
                    int nx = nowX + dx[dir];
                    int ny = nowY + dy[dir];  
                    if(isBound(nx,ny) || isWall(nx,ny)) {
                        if(visited[nowX][nowY]) {
                            break;
                        }
                        q.add(new Pos(nowX, nowY, now.time+1));
                        visited[nowX][nowY] = true;
                        break;
                    }
                    nowX = nx;
                    nowY = ny;
                }
            }
            
        }
        
        return answer == 0 ? -1 : answer;
    }
    
    public static boolean isBound(int nx, int ny) {
        if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isWall(int nx, int ny) {
        if(map[nx][ny] == 'D') {
            return true;
        } else {
            return false;
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
}