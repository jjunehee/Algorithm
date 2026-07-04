
import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        int len = String.valueOf(storey).length();
        boolean flag = false;
        // 맨오른쪽 자릿수부터 반올림 해가며 카운팅
        while(true) {
            // 일의 자리 숫자 확인
            int check = storey % 10;
            
            if(check != 0) {
                // 일의 자리 숫자가 5보다 크면 올가는게 이득
                if(flag || check > 5) {
                    storey += 10 - check;
                    answer += 10 - check;
                } else if (check == 5) {
                  if(flag && storey/10 >= 5) {
                    storey += 10 - check;
                    answer += 10 - check;
                  } else {
                    storey -= check;   
                    answer += check;  
                  }
                } else {
                    storey -= check;   
                    answer += check;
                }    
            }
            
            // 도착 및 다음 작업을 위한 세팅
            if(storey == 0) {
                break;
            } else {
                storey /= 10;   
                flag = false;
            }
        }
        
        return answer;
    }
}