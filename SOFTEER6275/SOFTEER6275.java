package SOFTEER6275;

import java.util.*;
import java.io.*;

public class SOFTEER6275 {

    static int N,M;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 맵 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        //  맵 입력
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 끝점 찾기
        Pos startPos = searchStartPos();
        int startDir = searchStartDir(startPos);

        // 끝점에서 부터 이제 탐색 시작
        startSimulation(startPos, startDir);

        System.out.print(sb.toString());

    }

    public static void startSimulation(Pos startPos, int startDir) {
        int nowX = startPos.x;
        int nowY = startPos.y;
        int nowDir = startDir;

        int nx,ny;
        while(true) {

            boolean moveCheck = false;
            
            if(!moveCheck) {
                // 정면 확인
                nx = nowX + dx[nowDir];
                ny = nowY + dy[nowDir];
                if(!isBound(nx, ny) && map[nx][ny] == '#') {
                    nowX = nowX + 2*dx[nowDir];
                    nowY = nowY + 2*dy[nowDir];
                    sb.append("A");
                    moveCheck = true;
                }
            }
            
            if(!moveCheck) {
                // 왼쪽 확인
                nx = nowX + dx[(nowDir + 3) % 4];
                ny = nowY + dy[(nowDir + 3) % 4];
                if(!isBound(nx, ny) && map[nx][ny] == '#') {
                    nowX = nowX + 2*dx[(nowDir + 3) % 4];
                    nowY = nowY + 2*dy[(nowDir + 3) % 4];
                    nowDir = (nowDir + 3) % 4;
                    sb.append("L");
                    sb.append("A");
                    moveCheck = true;
                }
            }

            if(!moveCheck) {
                // 오른쪽 확인
                nx = nowX + dx[(nowDir + 1) % 4];
                ny = nowY + dy[(nowDir + 1) % 4];
                if(!isBound(nx, ny) && map[nx][ny] == '#') {
                    nowX = nowX + 2*dx[(nowDir + 1) % 4];
                    nowY = nowY + 2*dy[(nowDir + 1) % 4];
                    nowDir = (nowDir + 1) % 4;
                    sb.append("R");
                    sb.append("A");
                    moveCheck = true;
                }
            }

            if(!moveCheck) {
                break;
            }
            
        }
    }

    public static Pos searchStartPos() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == '#') {

                    int roadCnt = 0;
                    int nx,ny;
                    for(int dir=0; dir<4; dir++) {
                        nx = i + dx[dir];
                        ny = j + dy[dir];
                        if(isBound(nx, ny)) {
                            continue;
                        }
                        if(map[nx][ny] == '#') {
                            roadCnt++;
                        }
                    }

                    // 주변 road가 1개라면 그곳은 끝점
                    if(roadCnt==1) {

                        sb.append(i+1).append(" ").append(j+1).append("\n");
                        return new Pos(i,j);
                    }
                }
            }
        }

        return null;
    }

    public static int searchStartDir(Pos startPos) {
        int nx, ny;
        for(int dir=0; dir<4; dir++) {
            nx = startPos.x + dx[dir];
            ny = startPos.y + dy[dir];
            if(isBound(nx, ny)) {
                continue;
            }
            if(map[nx][ny] == '#') {
                switch (dir) {
                    case 0 :
                        sb.append("v").append("\n");
                        break;
                    case 1:
                        sb.append("<").append("\n");
                        break;
                    case 2:
                        sb.append("^").append("\n");
                        break;
                    case 3:
                        sb.append(">").append("\n");
                        break;
                }
                return dir;
            }
        }

        return -1;
    }

    public static boolean isBound(int nx, int ny) {

        if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
            return true;
        } else {
            return false;
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