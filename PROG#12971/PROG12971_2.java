import java.util.*;

class Solution {
    
    static int[][] dp;
    static int max;
    
    public int solution(int sticker[]) {
        int size = sticker.length;
        if(size==1) {
            return sticker[0];
        }
        
        dp = new int[size][2];
        
        
        max = Integer.MIN_VALUE;
        calculate(sticker, 0, size-2);
        calculate(sticker, 1, size-1);

        return max;
    }
    
    public static void calculate(int[] sticker, int start, int end) {
        
        dp[start][0] = 0;
        dp[start][1] = sticker[start];
        
        for(int i=start+1; i<=end; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + sticker[i];
        }
        
        for(int[] value : dp) {
            int tmp = Math.max(value[0],value[1]);
            max = Math.max(max, tmp);
        }
        
    }
}