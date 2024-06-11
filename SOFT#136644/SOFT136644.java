
import java.io.*;
import java.util.*;

public class SOFT136644 {

    static int min = Integer.MAX_VALUE;
    static boolean[] visited;
    static int N,M,K;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] railNum = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            railNum[i] = Integer.parseInt(st.nextToken());
        }

        permu(1,0,0,railNum,0);
        System.out.println(min);
    }

    public static void permu(int cnt, int now, int sum, int[] railNum, int resetCnt) {

        if(resetCnt == N) {
            for(int i=0; i<N; i++) {
                visited[i] = false;
            }
        }
        // System.out.println(cnt);
        if(cnt == K) {
            min = Math.min(min, sum);
            return;
        }

        for(int i=0; i<N; i++) {
            if(visited[i]) {
                continue;
            }

            visited[i] = true;
            if(now + railNum[i] > M) {
                permu(cnt + 1, railNum[i], sum + railNum[i], railNum, resetCnt + 1);
            } else {
                permu(cnt, now + railNum[i], sum + railNum[i], railNum, resetCnt + 1);
            }
            visited[i] = false;
        }
    }
}
