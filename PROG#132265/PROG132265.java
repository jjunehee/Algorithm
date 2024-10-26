

import java.util.*;

class PROG132265 {
    public int solution(int[] topping) {
        int answer = 0;
        
        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        
        for(Integer ingredient : topping) {
            rightMap.put(ingredient, rightMap.getOrDefault(ingredient,0) + 1);
        }
        
        for(int i=0; i<topping.length; i++) {
            
            if(rightMap.containsKey(topping[i])) {
                rightMap.put(topping[i], rightMap.get(topping[i]) - 1);
                if(rightMap.get(topping[i]) == 0) {
                    rightMap.remove(topping[i]);
                }
            }
            
            leftMap.put(topping[i],leftMap.getOrDefault(topping[i],0) + 1);
            
            if(leftMap.size() == rightMap.size()) {
                answer++;
            }
            
        }
        
        return answer;
    }
}