class PROG12924 {
    public int solution(int n) {
        int answer = 0;
        
        for(int i=1; i<=n; i++) {
            if(dfs(i,n)) answer++; 
        }
        return answer;
    }
    
    boolean dfs(int num, int check) {
        int sum = 0;
        for(int i=num; i<=check; i++) {
            sum += i;
            if(check == sum) {
                return true;
            } else if(check < sum) {
                return false;
            }
        }
        return false;
    }
}