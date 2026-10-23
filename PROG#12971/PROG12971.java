import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int size = sticker.length;
        if(size == 1) {
            return sticker[0];
        }
        
        int[][] dp = new int[size][2];
        boolean[] disabled = new boolean[size];
        disabled[size-1] = true;
        
        for(int i=0; i<size; i++) {
            int before = (i-1+size)%size;
            dp[i][0] = Math.max(dp[before][1], dp[before][0]);
            if(!disabled[i]) {
                dp[i][1] = dp[before][0] + sticker[i];    
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int[] value : dp) {
            int tmp = Math.max(value[0], value[1]);
            max = Math.max(max, tmp);
        }
        
        dp = new int[size][2];
        disabled = new boolean[size];
        disabled[0] = true;
        
        for(int i=0; i<size; i++) {
            int before = (i-1+size)%size;
            dp[i][0] = Math.max(dp[before][1], dp[before][0]);
            if(!disabled[i]) {
                dp[i][1] = dp[before][0] + sticker[i];    
            }
        }
        
        for(int[] value : dp) {
            int tmp = Math.max(value[0], value[1]);
            max = Math.max(max, tmp);
        }

        return max;
    }
}