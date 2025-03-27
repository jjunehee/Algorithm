import java.util.*;

class PROG49189 {
    
    static List<Integer>[] graphInfo;
    static int[] minDist;
    
    public int solution(int N, int[][] graph) {
        
        graphInfo = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graphInfo[i] = new ArrayList<>();
        }

        for(int i=0; i<graph.length; i++) {
            int v1 = graph[i][0];
            int v2 = graph[i][1];
            graphInfo[v1].add(v2);
            graphInfo[v2].add(v1);
        }

        searchDistByBfs(N);
        int max = Integer.MIN_VALUE;
        for(int i=0; i<minDist.length; i++) {
            max = Math.max(max,minDist[i]);
        }
        int answer = 0;
        for(int i=0; i<minDist.length; i++) {
            if(max == minDist[i]) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public void searchDistByBfs(int N) {
        // 순회를 시작하는 정점 1
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(1,0));
        // 정점 1로부터의 최단 거리를 저장하는 배열
        minDist = new int[N+1];
        // 방문 여부
        boolean[] visited = new boolean[N+1];
        visited[1] = true;

        //  순회 시작
        while(!q.isEmpty()) {

            Pos now = q.poll();

            for(int i=0; i<graphInfo[now.v].size(); i++) {
                int next = graphInfo[now.v].get(i);
                if(visited[next]) {
                    continue;
                }
                minDist[next] = now.dist+1;
                visited[next] = true;
                q.add(new Pos(next, now.dist+1));
            }
        }

    }

    public class Pos {
        int v;
        int dist;
        public Pos(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }
}