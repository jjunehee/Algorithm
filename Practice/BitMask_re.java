
import java.util.*;

public class BitMask_re {


    public static void main(String[] args) {
        BitSolution solution = new BitSolution();

        String[] input = {
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

        solution.solve(input);

    }

}

class BitSolution {

    static String[][] map;
    static List<Point> pointList;
    static List<Point> targetList;
    static int[][] dist;

    public void solve(String[] input) {

        map = new String[input.length][input[0].length()];
        pointList = new ArrayList<>();
        targetList = new ArrayList<>();
    
        // map 입력
        for(int i=0; i<map.length; i++) {
            String str = input[i];
            for(int j=0; j<str.length(); j++) {
                String s = str.substring(j,j+1);
                if("S".equals(s)) {
                    // 출발 지점 저장
                    pointList.add(new Point(i,j));
                } else if ("T".equals(s)) {
                    // 목표 지점 저장
                    targetList.add(new Point(i,j));
                }
                // 맵 저장
                map[i][j] = s;
            }
        }

        // (출발 지점 + 타겟 지점) = 모든 지점 정보
        for(Point point: targetList) {
            pointList.add(new Point(point.x, point.y));
        }

        printMap();

        // 일단 모든 지점간 거리를 구한다.
        calculateDist();

        int ret = calculateMin();
        System.out.println(ret);
    }

    public int calculateMin() {
        int min = Integer.MAX_VALUE;

        if(targetList.size() == 0) {
            return 0;
        }

        int totalMask = 1 << targetList.size();
        int pointCount = pointList.size();

        int[][] dp = new int[totalMask][pointCount];
        for(int i=0; i<totalMask; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        for(int mask=0; mask < totalMask; mask++) {

            for(int cur=0; cur < pointCount; cur++) {

                if(dp[mask][cur] == Integer.MAX_VALUE) {
                   continue;
                }

                for(int next=0; next < targetList.size(); next++) {
                    // 방문 체크
                    int nextBit = 1 << next;
                    if((mask & nextBit) != 0) {
                        continue;
                    }

                    int nextPoint = next + 1;
                    if(dist[cur][nextPoint] == -1) {
                        continue;
                    }

                    int newDist = dp[mask][cur] + dist[cur][nextPoint];
                    int newMask = mask | nextBit;

                    dp[newMask][nextPoint] = Math.min(dp[newMask][nextPoint], newDist);
                }

            }
        }
        

        int fullMask =  totalMask - 1;
        for(int point=1; point < pointList.size(); point++) {
            min = Math.min(dp[fullMask][point], min);
        }

        
        return min == Integer.MAX_VALUE ? -1 :min;
    }

    public void calculateDist() {
        dist = new int[pointList.size()][pointList.size()];
        for(int i=0; i<pointList.size(); i++) {
            Arrays.fill(dist[i], -1);
        }

        for(int i=0; i<pointList.size(); i++) {

            int[][] bfsInfo = getDistByBfs(i);
            for(int j=0; j<pointList.size(); j++) {
                Point to = pointList.get(j);
                dist[i][j] = bfsInfo[to.x][to.y];
            }
        }
    }

    public int[][] getDistByBfs(int pointIdx) {


        // 출발점부터의 각 지점까지의 거리 변수
        int[][] distFromStart = new int[map.length][map[0].length];
        for(int i=0; i<map.length; i++) {
            Arrays.fill(distFromStart[i], -1);
        }

        // 방향 변수
        int[] dx = {-1,0,1,0};
        int[] dy = {0,-1,0,1};

        Point start = pointList.get(pointIdx);
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(start.x, start.y,0));
        distFromStart[start.x][start.y] = 0;

        while(!q.isEmpty()) {
            Point now = q.poll();

            for(int dir=0; dir<4; dir++) {
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];

                if(isBound(nx, ny) || distFromStart[nx][ny] != -1 || map[nx][ny].equals("X")) {
                    continue;
                }

                distFromStart[nx][ny] = now.dist+1;
                q.add(new Point(nx,ny, now.dist+1));
            }
        }

        return distFromStart;
    }

    public boolean isBound(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length) {
            return true;
        } else {
            return false;
        }
    }

    public void printMap() {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static class Point {
        int x,y;
        int dist;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}


