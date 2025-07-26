
import java.io.*;
import java.util.*;

class Solution {
    public String solution(int[] numbers) {    
        String[] numStr = new String[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            numStr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(numStr, (o1,o2) -> (o2+o1).compareTo(o1+o2));
        
        if(numStr[0].equals("0")) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for(String str : numStr) {
            sb.append(str);
        }
        return sb.toString();
    }
}