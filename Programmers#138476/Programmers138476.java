import java.util.*;
import java.io.*;

class Programmers138476 {
    public int solution(int k, int[] tangerine) {
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for(Integer num : tangerine) {
            map.compute(num, (key,cnt) -> cnt == null ? 1 : cnt + 1);
        }
        
        List<Integer> list = new ArrayList<>();
        Object[] keySet = map.keySet().toArray();
        for(Object key : keySet) {
            Integer keyInt = Integer.parseInt(String.valueOf(key));
            list.add(map.get(keyInt));
        }
        
        Collections.sort(list);
        
        int ret = 0;
        int size = list.size();
        for(int i=size-1; i>=0; i--) {
            
            ret++;
            k -= list.get(i);
            if(k<=0) {
                break;
            }
        }
        
        return ret;
    }
    
    public static void main(String[] args) {
    	Programmers138476 test = new Programmers138476();
    	int[] tangerine = {1,2,3,4,5,6};
    	test.solution(6, tangerine);
	}
}