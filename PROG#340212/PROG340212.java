import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int left = 1;
        int right = -1;
        for(int diff : diffs) {
            right = Math.max(diff, right);
        }
        
        int minTime = Integer.MAX_VALUE;
        while(left <= right) {
            int middle = (left+right)/2;
            
            long remain = calculate(diffs, times, limit, middle);
            
            if(remain > 0) {
                minTime = Math.min(middle, minTime);
                right = middle-1;
            } else if (remain < 0) {
                left = middle+1;
            } else {
                minTime = Math.min(middle, minTime);
                break;
            }
        }
        
        return minTime;
    }
    
    public long calculate(int[] diffs, int[] times, long limit, int level) {
        
        int time_prev = 0;
        
        long Time = 0;
        for(int i=0; i<diffs.length; i++) {
            
            int diff = diffs[i];
            int time_cur = times[i];
            
            if(diff <= level) {
                Time += time_cur;
            } else if(diff > level) {
                Time += ((diff - level) * (time_cur + time_prev));
                Time += time_cur;
            }
            
            time_prev = time_cur;
        }
        
        return limit - Time;
    }
}