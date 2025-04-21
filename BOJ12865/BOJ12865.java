package BOJ12865;

import java.util.*;
import java.io.*;

public class BOJ12865 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Item[] itemAray = new Item[N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            itemAray[i] = new Item(W,V);
        }

        // dp[i][k] : i번쨰 물품까지 사용하면서, k 무게를 채웠을떄의 최대 가치
        int[][] dp = new int[N+1][K+1];
        for(int weight = 1; weight <= K; weight++) {
            for(int i=1; i<=N; i++) {
                dp[i][weight] = dp[i-1][weight];
                if(weight - itemAray[i].weight >= 0) {
                    dp[i][weight] = Math.max(dp[i-1][weight], itemAray[i].value + dp[i-1][weight - itemAray[i].weight]);
                }
            }
        }

        System.out.print(dp[N][K]);

    }

    public static class Item {
        int weight;
        int value;
        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
