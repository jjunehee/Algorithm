import java.io.*;


public class BOJ2156 {

    static int N;
    static int[] dp;
    static int[] wineInfo;
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 포도주 갯수
        N = Integer.parseInt(br.readLine());

        wineInfo = new int[N+1];
        for(int i=1; i<=N; i++) {
            wineInfo[i] = Integer.parseInt(br.readLine());
        }

        // dp[i] : i번쨰까지 먹은 최대 포도주 양
        dp = new int[N+1];

        // 초기화
        dp[0] = 0;
        dp[1] = wineInfo[1];
        dp[2] = wineInfo[1] + wineInfo[2];

        for(int i=3; i<=N; i++) {
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-3] + wineInfo[i-1] + wineInfo[i], dp[i-2] + wineInfo[i]));
        }

        System.out.println(dp[N]);
    }
}
