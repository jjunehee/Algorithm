
import java.util.*;
import java.io.*;

public class BOJ1687_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, K
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        
        boolean[] visited = new boolean[100001];
        Queue<Pos> q = new LinkedList<>();
        
        // 시작점
        visited[N] = true;
        q.add(new Pos(0, N));
        
        int answer = 0;
        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(now.idx == K) {
                answer = now.time;
                break;
            }

            // 좌
            if(now.idx - 1 >= 0 && !visited[now.idx-1]) {
                q.add(new Pos(now.time+1, now.idx - 1));
                visited[now.idx-1] = true;
            }
            // 우
            if(now.idx + 1 <= 100000 && !visited[now.idx+1]) {
                q.add(new Pos(now.time+1, now.idx + 1));
                visited[now.idx+1] = true;
            }
            // 2배
            if(now.idx * 2 <= 100000 && !visited[now.idx*2]) {
                q.add(new Pos(now.time+1, now.idx*2));
                visited[now.idx*2] = true;
            }

        }

        System.out.print(answer);

    }

    public static class Pos{
        int time;
        int idx;
        public Pos(int time, int idx) {
            this.time = time;
            this.idx = idx;
        }
    }
}
