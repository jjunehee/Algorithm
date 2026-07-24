import java.util.*;

public class BitMask {

    public static void main(String[] args) {
        BitMaskSolution solution = new BitMaskSolution();

        String[] map = {
            "S...X....",
            "..X...T..",
            ".....X...",
            ".X..T....",
            "......X..",
            "..T......",
            "...X.....",
            ".....T...",
            "........."
        };

        solution.solution(map);

    }
}

class BitMaskSolution {

    static String[][] realMap; // 이차원 배열로 변환한 맵
    static List<Point> targetList; // 타겟 리스트
    static List<Point> pointList; // 시작점 + 타겟 리스트
    static Point start; // 시작점
    static int[][] dist; // 타겟간의 거리배열

    // 이동 배열
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    public void solution(String[] map) {

        // 문자열 배열을 2차원 문자 배열로 변환
        realMap = new String[map.length][map[0].length()];
        pointList = new ArrayList<>();
        targetList = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length(); j++) {
                realMap[i][j] = map[i].substring(j, j + 1);
                if(realMap[i][j].equals("T")) {
                    targetList.add(new Point(i,j));
                } else if(realMap[i][j].equals("S")) {
                    start = new Point(i,j);
                    pointList.add(start);
                }
            }
        }
        for(Point target : targetList) {
            pointList.add(target);
        }

        // 타겟 간의 거리를 BFS로 구할 예정
        calculateDist();
        for(int i=1; i<pointList.size(); i++) {
            if(dist[0][i] == -1) {
                System.out.println(-1);        
                return;
            }
        }

        // 비트마스킹 + DP 를 통해 시작점을 기점으로 탐색
        int ret = calculateMin();

        System.out.println(ret);
    }

    public void calculateDist() {

        // 타겟 간의 거리 배열 선언
        dist = new int[pointList.size()][pointList.size()];
        for(int i=0; i<pointList.size(); i++) {
            Arrays.fill(dist[i],-1);
        }
 
        for(int i=0; i < pointList.size(); i++) {
            Point start = pointList.get(i);
            int[][] bfsResult = bfs(start);

            for(int j=0; j< pointList.size(); j++) {
                Point end = pointList.get(j);
                dist[i][j] = bfsResult[end.x][end.y];
            }
        }
    }

    public int calculateMin() {

        int pointCount = pointList.size();
        int targetCount = targetList.size();
        if(targetCount == 0) {
            return 0;
        }

        int totalMaskCount = 1 << targetCount;

        // dp[i][j] :방문상태,현재위치 기준 소요기간
        int[][] dp = new int[totalMaskCount][pointCount];
        for(int i=0; i<totalMaskCount; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        for(int mask=0; mask<totalMaskCount; mask++) {
            for(int cur=0; cur<poindtCount; cur++) {

                if(dp[mask][cur] == Integer.MAX_VALUE) {
                    continue;
                }

                for(int next=0; next < targetCount; next++) {

                    int nextBit = 1 << next;
                    // 이미 방문한 적이 있는 곳이면, 패스
                    if((mask & nextBit) != 0) {
                        continue;
                    }
                    
                    int nextPoint = next + 1;
                    // 현재위치에서 못가는 곳이면 패스
                    if(dist[cur][nextPoint] == -1) {
                        continue;
                    }

                    int newDist = dp[mask][cur] + dist[cur][nextPoint];
                    int newMask = mask | nextBit;

                    dp[newMask][nextPoint] = Math.min(dp[newMask][nextPoint], newDist);
                    
                }

            }
        }

        int fullMask = totalMaskCount - 1;

        int min = Integer.MAX_VALUE;
        for(int i=0; i<pointCount; i++) {
            min = Math.min(min, dp[fullMask][i]);
        }
        if(min == Integer.MAX_VALUE) {
            return -1;
        }

        return min;
    }

    public int[][] bfs(Point start) {

        int[][] dist = new int[realMap.length][realMap[0].length];
        for(int i=0; i<realMap.length; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<Point> q = new LinkedList<>();
        q.add(start);
        dist[start.x][start.y] = 0;
    
        while(!q.isEmpty()) {
            Point now = q.poll();

            for(int dir=0; dir<4; dir++) {
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];

                // 경계를 넘어섰거나, 이미 방문했다면 패스
                if(isBound(nx,ny) || dist[nx][ny] != -1 || realMap[nx][ny].equals("X")) {
                    continue;
                }
                dist[nx][ny] = dist[now.x][now.y] + 1;
                q.add(new Point(nx,ny));
            }
        }

        return dist;
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean isBound(int nx, int ny) {
        if(nx < 0 || nx >= realMap.length || ny < 0 || ny >= realMap[0].length) {
            return true;
        } else {
            return false;
        }
    }

    public void printMap() {
        for (int i = 0; i < realMap.length; i++) {
            for (int j = 0; j < realMap[i].length; j++) {
                System.out.print(realMap[i][j] + " ");
            }
            System.out.println();
        }
    }
}