import java.io.*;
import java.util.*;

public class BOJ6118_2 {
    
    static List<Integer>[] graphInfo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 그래프 정보 초기화
        graphInfo = new List[N+1];
        for(int v=1; v<=N; v++) {
            graphInfo[v] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graphInfo[a].add(b);
            graphInfo[b].add(a);
        }

        boolean[] visited = new boolean[N+1];
        Queue<Pos> q = new LinkedList<>();
        PriorityQueue<Pos> pq = new PriorityQueue<>();

        // 시작점 초기화
        visited[1] = true;
        q.add(new Pos(1,0));

        while(!q.isEmpty()) {
            
            Pos now = q.poll();
            for(Integer next : graphInfo[now.idx]) {
                if(visited[next]) {
                    continue;
                }
                q.add(new Pos(next, now.dist+1));
                pq.add(new Pos(next, now.dist+1));
                visited[next] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        Pos farPos = pq.poll();


        int sameCnt = 1;
        while(!pq.isEmpty()) {
            Pos now = pq.poll();
            if(farPos.dist == now.dist) {
                sameCnt++;
            } else {
                break;
            }
        }
        sb.append(farPos.idx)
            .append(" ")
            .append(farPos.dist)
            .append(" ")
            .append(sameCnt);

        System.out.print(sb.toString());

    }

    public static class Pos implements Comparable<Pos> {
        int idx;
        int dist;
        public Pos(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.dist == o.dist) {
                return this.idx - o.idx;
            } else {
                return o.dist - this.dist;
            }
        }
    }
}
