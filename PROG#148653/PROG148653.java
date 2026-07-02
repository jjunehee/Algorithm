import java.util.*;

class Solution {

    // dp[i] : i층까지 도달하는데 걸리는 최소 비용
    static int[] dp;
    
    public int solution(int storey) {
        int answer = 0;
        
        // 일단 최대 크기까지 만들자.
        dp = new int[100000001];
        Arrays.fill(dp, Integer.MAX_VALUE);
    
        int limit = (int) Math.pow(10, (int) String.valueOf(storey).length() + 1);
        // System.out.println(limit);
        dfs(storey, 0, limit);
        
        
        return dp[0];
    }
    
    public static void dfs(int now, int cost, int limit) {
        
        if(now > limit) {
            return;
        }
        
        // 최종 목적지인 0층에 도달하면 최솟값 갱신하고 더이상 탐색 불필요
        if(now == 0) {
            dp[now] = Math.min(dp[now], cost);
            return;
        }
        
        for(int c=8; c>=0; c--) {
            int num = now + (int)Math.pow(10,c);
            // num이 범위 안에 있으면 진행
            if(num >= 0 && num <= 100000000) {
                // 그리고 dp[now] 보다 적은 비용으로 도달한 것이라면
                if(dp[num] > cost + 1) {
                    // 그 값으로 최솟값 갱신한 후 레츠고
                    dp[num] = cost + 1;
                    dfs(num, cost+1, limit);
                }
            }
        }
        
        for(int c=8; c>=0; c--) {
            int num = now - (int)Math.pow(10,c);
            // num이 범위 안에 있으면 진행
            if(num >= 0 && num <= 100000000) {
                // 그리고 dp[now] 보다 적은 비용으로 도달한 것이라면
                if(dp[num] > cost + 1) {
                    // 그 값으로 최솟값 갱신한 후 레츠고
                    dp[num] = cost + 1;
                    dfs(num, cost+1, limit);
                }
            }
        }
    }
}