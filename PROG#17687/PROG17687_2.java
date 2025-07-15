class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
    
        String totalStr = "";
        for(int i=0; i<t*m; i++) {
            totalStr += Integer.toString(i,n);
        }
        
        int cnt = 0;
        int endCnt = 0;
        for(int i=0; i<totalStr.length(); i++) {
            cnt++;
            if(cnt == p) {
                answer.append(totalStr.charAt(i));
                endCnt++;
                if(endCnt == t) {
                    break;
                }
            }
            if(cnt == m) {
                cnt = 0;
            }
        }

        
        return answer.toString().toUpperCase();
    }
}