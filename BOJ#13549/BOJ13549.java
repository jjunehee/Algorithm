
import java.util.*;
import java.io.*;

public class BOJ13549 {

    static int[] dp;
    static boolean[] visited;
    static PriorityQueue<Pos> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N K
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // dp[i] : i 까지 도달하는데 드는 최소 비용
        dp = new int[100001];
        visited = new boolean[100001];

        // 초기화 (TODO 필요성 체크)
        for(int i=0; i<=100000; i++) {
            dp[i] = Integer.MAX_VALUE; 
        }
        
        dp[N] = 0;

        if(!isBound(N+1)) {
            pq.add(new Pos(N + 1, dp[N] + 1));
        }
        if(!isBound(N-1)) {
            pq.add(new Pos(N - 1, dp[N] + 1));
        }
        if(!isBound(N*2)) {
            pq.add(new Pos(N * 2, dp[N]));
        }
        // 다익스트라
        System.out.print(searchByDijkstra(K));
    }

    public static int searchByDijkstra(int end) {

        while(!pq.isEmpty()) {

            // 현재 갈 수 있는 곳 중에 최단거리 간선 선택
            Pos now = pq.poll();
            if(now.vertex == end) {
                return now.weight;
            }
            
            // 해당 정점에 이미 방문한 적이 있다면, 이미 최단시간으로 방문한 것이므로 스킵
            if(visited[now.vertex]) {
                continue;
            }

            // 방문처리 및 최단시간 기록
            visited[now.vertex] = true;
            dp[now.vertex] = now.weight;

            if(!isBound(now.vertex*2) && !visited[now.vertex*2]) {
                pq.add(new Pos(now.vertex*2, now.weight));
            }
            if(!isBound(now.vertex+1) && !visited[now.vertex+1]) {
                pq.add(new Pos(now.vertex+1, now.weight + 1));
            }
            if(!isBound(now.vertex-1) && !visited[now.vertex-1]) {
                pq.add(new Pos(now.vertex-1, now.weight + 1));
            }
    
        }

         return -1;
    }

    public static boolean isBound(int num) {
        if(num < 0 || num > 100000) {
            return true;
        } else {
            return false;
        }
    }

    public static class Pos implements Comparable<Pos> {
        int vertex;
        int weight;
        public Pos(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pos o) {
            return this.weight - o.weight;
        }
    }

}