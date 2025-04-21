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

        for(int k = 1; k <= K; k++) { // 버틸 수 있는 무게
            for(int i=1; i<=N; i++) { // 물품
                // 초기화 : 이전 물품까지 k 무게를 채웠을때의 최대 가치
                dp[i][k] = dp[i-1][k];
                if(k - itemAray[i].weight >= 0) { // 이번 차례의 물품을 버틸 수 있는 무게라면,
                    // 비교
                    // 1. 이전(i-1번) 물품까지 k 무게를 채웠을때의 최대 가치
                    // 2. 이번 차례의 물품을 추가했었다면 얻을 가치
                    dp[i][k] = Math.max(dp[i-1][k], itemAray[i].value + dp[i-1][k - itemAray[i].weight]);
                }
            }
        }

        // N번 물품까지 사용하면서 K 무게를 채웠을때 최대 가치
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
