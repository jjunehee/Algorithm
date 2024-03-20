import java.util.*;

public class PROG12980 {
    
    static long min = Integer.MAX_VALUE;
    public long solution(int n) {

        
        int answer = 0;
        
        while(n!=0) {
            if(n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
                answer++;
            }
        }
 
        return answer;
    }
}