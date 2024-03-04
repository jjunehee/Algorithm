import java.util.*;

class PROG42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Queue<Integer> q = new LinkedList<>();
        boolean[] done = new boolean[progresses.length];
        int status = 0;
        int endCnt = 0;
        while(true) {
            
            for(int i=0; i<progresses.length; i++) {
                if(done[i]) continue;
                
                progresses[i] += speeds[i];
                if(progresses[i] >= 100) {
                    done[i] = true;
                }
            }
            
            int cnt = 0;
            for(int i=status; i<progresses.length; i++) {
                if(done[i]) {
                    cnt++;
                } else {
                    status = i;
                    break;
                }
            }
            if(cnt > 0) {
                q.add(cnt);
                endCnt += cnt;
                if(endCnt == progresses.length) {
                    break;
                }
            }
        }
        
        int[] answer = new int[q.size()];
        
        int idx = 0;
        while(!q.isEmpty()) {
            answer[idx++] = q.poll();
        }
        
        return answer;
    }
}