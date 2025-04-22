class PROG42898_2 {
    
    static int N,M;
    static int[][] map;
    static int[][] dp;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        N = n;
        M = m;
        map = new int[N][M];
        dp = new int[N][M];
        dp[0][0] = 1;
        int puddleCnt = puddles.length;
        for(int i=0; i<puddleCnt; i++) {
            map[puddles[i][1]-1][puddles[i][0]-1] = -1;
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                
                if(map[i][j] == -1) {
                    continue;
                }
                
                if(i-1 >= 0 && map[i-1][j] != -1) {
                    dp[i][j] += dp[i-1][j];
                }
                
                if(j-1 >= 0 && map[i][j-1] != -1) {
                    dp[i][j] += dp[i][j-1];
                }
                
                dp[i][j] %= 1000000007;
            }
        }
        
        return dp[N-1][M-1];
    }
}