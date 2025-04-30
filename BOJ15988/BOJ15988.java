package BOJ15988;

import java.io.*;

public class BOJ15988 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 갯수
        int N = Integer.parseInt(br.readLine());
        
        long[] dp = new long[1000001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i=4; i<=1000000; i++) {
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1000000009;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }

        System.out.print(sb.toString());
    }
}
