import java.io.*;
import java.util.*;

public class softeer3 {

  static int N;
  static int M;
  static int[][] map;
  static boolean[][] isWall;
  static  Target[] targetArray;
  static boolean[][] visited;
  static boolean[] targetVisited;
  static int ret;

  static int[] dx = {1,0,-1,0};
  static int[] dy = {0,-1,0,1};
  public static void main(String[] args) throws IOException{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N+1][N+1];
    isWall = new boolean[N+1][N+1];

    for(int i=1; i<=N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=1; j<=N; j++) {
        int num = Integer.parseInt(st.nextToken());
        if(num == 1) {
          isWall[i][j] = true;
        }
      }
    }

    targetArray = new Target[M+1];
    for(int i=1; i<=M; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      map[x][y] = i;
      targetArray[i] = new Target(x,y);
    }

    visited = new boolean[N+1][N+1];
    targetVisited = new boolean[M+1];

    targetVisited[1] = true;
    dfs(targetArray[1].x,targetArray[1].y);

    System.out.println(ret);
      
  }

  public static void dfs(int x,int y) {

    if(x == targetArray[M].x && y == targetArray[M].y) {
      ret++;
      return;
    }

    int nx,ny;
    for(int dir=0; dir<4; dir++) {
      nx = x + dx[dir];
      ny = y + dy[dir];

      // 맵 밖이거나 
      if(isBounded(nx,ny)) {
        continue;
      }
      // 벽이 있거나 이미 방문한 곳이면 못 간다.
      if(isWall[nx][ny] || visited[nx][ny]) {
        continue;
      }

      // 그곳이 target일떄
      if(map[nx][ny] != 0) {

        // 그곳에 가려면 선행 조건 target을 방문 했어야한다.
        if(targetVisited[map[nx][ny] - 1]) {

          // 선행 조건 충족한다면 방문 할 수 있다.
          visited[nx][ny] = true;
          targetVisited[map[nx][ny]] = true;
          dfs(nx,ny);
          targetVisited[map[nx][ny]] = false;
          visited[nx][ny] = false;
        } else { // 선행 조건 충족 하지 않으면 못 가는 곳.
          continue;
        }
      } else { // 그냥 갈 수 있는 빈칸일때
        visited[nx][ny] = true;
        dfs(nx,ny);
        visited[nx][ny] = false;
      }
      
    }
    
  }

  public static boolean isBounded(int nx, int ny) {

    if(nx < 1 || nx > N || ny < 1 || ny > N) {
      return true;
    }

    return false;
  }

  public static class Target {
    int x,y;
    public Target(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  
}