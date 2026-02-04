import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        int idx = 0;
        for(Long num : numbers) {
            // 이진수 변환
            String numBinary = Long.toString(num,2);
            // 2개 이하로 다른 비트를 찾아낸다.
            long ret = calculate(numBinary);
            answer[idx++] = ret;
        }
        
        return answer;
        
    }
    
    public long calculate(String numBinary) {
        
        int idxCheck = numBinary.length();
        for(int i=numBinary.length()-1; i>=0; i--) {
            if(numBinary.charAt(i) == '1') {
                
                if(i==0) {
                    idxCheck = i;
                    break;
                }
                if(i-1 >= 0 && numBinary.charAt(i-1) == '0') {
                    idxCheck = i;
                    break;
                } else {
                    continue;        
                }
                
            } else {
                idxCheck = i;
                break;
            }
        }
        
        return Long.parseLong(numBinary,2) + (long)Math.pow(2,numBinary.length()-idxCheck-1);
        
    }
}