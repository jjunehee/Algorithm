

import java.util.*;
import java.io.*;

public class BOJ13549 {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N K
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // dp[i] : i 까지 도달하는데 드는 최소 비용
        dp = new int[100001];

        // 초기화 (TODO 필요성 체크)
        for(int i=1; i<=100000; i++) {
            dp[i] = Integer.MAX_VALUE; 
        }

        dp[N] = 0;
        searchByRecur(N, 0);

        System.out.print(dp[1]);
    }

    public static void searchByRecur(int idx, int time) {

        // System.out.print(idx + " " + time);

        // 순간이동
        if(idx*2 <= 100000 && dp[idx] < dp[idx*2]) {
            // System.out.println("순간이동");
            dp[idx*2] = dp[idx];
            searchByRecur(idx*2, time);
        }
        // 우
        if(idx+1 <= 100000 && dp[idx] + 1 < dp[idx+1]) {
            // System.out.println("좌");
            dp[idx+1] = dp[idx] + 1;
            searchByRecur(idx+1, time+1);
        }
        // 좌
        if(idx-1 >= 1 && dp[idx] + 1 < dp[idx-1]) {
            // System.out.println("우");
            dp[idx-1] = dp[idx] + 1;
            searchByRecur(idx-1, time+1);
        }  

    }
}