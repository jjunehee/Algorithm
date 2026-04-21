class Solution {
    
    static int[][] dp;

    int solution(int[][] land) {
        
        
        // dp[i][j] : i행j열에 도달하기까지의 최대값
        dp = new int[land.length][4];
        
        // 1행 초기화
        for(int j=0; j<4; j++) {
            dp[0][j] = land[0][j];    
        }
        
        // 이전 행 중에 같은 열이 아닌 값이 최대값으로 내려올 수 있는 선택이다.
        for(int i=1; i<land.length; i++) {
            for(int j=0; j<4; j++) {
                dp[i][j] = land[i][j] + getBeforeRowMax(i-1,j);
            }
        }
        
        // 마지막 행의 dp[i][j]의 값은 해당 행까지 내려오는 최대값이다.
        int answer = Integer.MIN_VALUE;
        for(int j=0; j<4; j++) {
            answer = Math.max(answer, dp[land.length-1][j]);
        }

        return answer;
    }
    
    // 이전 행에서 같은 열을 제외하고 최대값을 뽑아내는 함수
    public int getBeforeRowMax(int i, int now) {
        
        int max = Integer.MIN_VALUE;
        for(int j=0; j<4; j++) {
            // 같은 열에 있으면, 스킵
            if(now==j) {
                continue;
            }
            max = Math.max(max, dp[i][j]);
        }
        return max;
    }
}