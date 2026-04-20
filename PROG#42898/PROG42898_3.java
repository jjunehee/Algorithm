class Solution {
    
    static int[][] map;
    static int[][] dp;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        map = new int[n][m];
        dp = new int[n][m];
        dp[0][0] = 1;
        
        for(int i=0; i<puddles.length; i++) {
            map[puddles[i][1]-1][puddles[i][0]-1] = -1;
        }
        
        for(int j=0; j<m; j++) {
            for(int i=0; i<n; i++) {
                
                if(map[i][j] == -1) {
                    continue;
                }
                
                if(i-1>=0 && map[i-1][j] != -1) {
                    dp[i][j] += dp[i-1][j];
                }
                if(j-1>=0 && map[i][j-1] != -1) {
                    dp[i][j] += dp[i][j-1];
                }
                
                dp[i][j] %= 1000000007;
            }
        }
        
        return dp[n-1][m-1] %= 1000000007;
    }

}