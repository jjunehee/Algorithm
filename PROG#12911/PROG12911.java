import java.util.*;
import java.io.*;

// 다음 큰 숫자
class BOJ12911 {
    public int solution(int n) {
        int answer = 0;
        int check = Integer.bitCount(n);
        for(int i=n+1; i<=1000000; i++) {
            if(check == Integer.bitCount(i)) {
                answer = i;
                break;
            }
        }
        return answer;
    }
}