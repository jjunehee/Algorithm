import java.util.*;

class Solution {
    public long solution(int[] weights) {
        // 정답 : 무게중립이 가능한 조합 개수
        long answer = 0;
        
        // 무게 배열을 순차 정렬한다. (시간초과 방지를 위해)
        Arrays.sort(weights);
        
        Map<Double, Integer> map = new HashMap<>();
        for(int weight : weights) {
                double num1 = weight*1.0;
                double num2 = weight*(2.0/3.0);
                double num3 = weight/2.0;
                double num4 = weight*(3.0/4.0);
                
                if(map.containsKey(num1)) answer+= map.get(num1);
                if(map.containsKey(num2)) answer+= map.get(num2);
                if(map.containsKey(num3)) answer+= map.get(num3);
                if(map.containsKey(num4)) answer+= map.get(num4);
                
                map.put(num1, map.getOrDefault(num1,0) + 1);
            
        }
        
        
        
        return answer;
    }
}