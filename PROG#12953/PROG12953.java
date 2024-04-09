class PROG12953 {
    public int solution(int[] arr) {
        
        int answer = arr[0];
        for(int i=0; i<arr.length; i++) {
            answer = lcm(answer,arr[i]);
        }
        
        return answer;
    }
    
    int lcm(int a, int b) {
        return (a*b) / gcd(a,b);
    }
    
    int gcd(int a, int b) {
        if(a%b == 0 ) {
            return b;
        }
        return gcd(b,a%b);
    }
}