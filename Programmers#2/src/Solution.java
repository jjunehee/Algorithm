import java.util.*;

// 네트워크
class Solution {
    static boolean[] visited;
    static int N;
    static int cnt;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        N = n;
        visited = new boolean[n];
        
        BFS(computers);        
        
        answer = cnt;
        return answer;
    }
    
    public void BFS(int[][] computers) {
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<N; i++) {
            if(visited[i]) {
                continue;
            }
            
            q.add(i);
            while(!q.isEmpty()) {
                int cur = q.poll();
                visited[cur] = true;
                for(int j=0; j<N; j++) {
                    if(cur != j && computers[cur][j] == 1 && !visited[j]) {
                        q.add(j);
                    }
                }
            }
            cnt++;
        }
    }
}