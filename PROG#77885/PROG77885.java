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
        
        int ret = 0;
        
        int idxCheck = numBinary.length();
        for(int i=numBinary.length()-1; i>=0; i--) {
            if(numBinary.charAt(i) == '1') {
                if(i-1 >= 0 && numBinary.charAt(i-1) == '0') {
                    idxCheck = i-1;
                    break;
                } else {
                    if(i-2 >=0 && numBinary.charAt(i-2) == '0') {
                        idxCheck = i-1;
                    } else {
                        continue;        
                    }
                }
                
            } else {
                idxCheck = numBinary.length() - i;
                break;
            }
        }
        
        return Long.parseLong(numBinary,2) + (long)Math.pow(2,idxCheck-1);
        
    }
}