import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static boolean[][] hasPath;
    static List<Integer>[] connectInfo;
    
    public int solution(int n, int[][] wires) {
        connectInfo = new List[n+1];
        for(int v=1; v<=n; v++) {
            connectInfo[v] = new ArrayList<>();
        }
        // 간선 여부 초기화
        hasPath = new boolean[n+1][n+1];
        for(int i=0; i<wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            changeFlag(v1,v2,true);
            connectInfo[v1].add(v2);
            connectInfo[v2].add(v1);
        
        }
        int minGap = Integer.MAX_VALUE;
        for(int i=0; i<wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            if(hasPath[v1][v2]) {
                changeFlag(v1,v2,false);
                minGap = Math.min(minGap, findGap(n));
                changeFlag(v1,v2,true);
            }
        }
        return minGap;
    }
    
    public static int findGap(int n) {
        boolean[] visited = new boolean[n+1];
        // 정점들을 전체적으로 둘러본다.
        int first = 0;
        int second = 0;
        for(int v=1; v<=n; v++) {
            // 방문하지 않았던 정점부터 너비우선탐색 진행
            if(!visited[v]) {
                Queue<Integer> q = new LinkedList<>();
                q.add(v); // 방문 q에 넣고
                visited[v] = true; // 방문 여부 체크
                first++;
                while(!q.isEmpty()) {
                    int now = q.poll(); // 정점 뽑기
                    // 그 정점과 연결된 정점 조회
                    for(int nextV : connectInfo[now]) {
                        // 이미 한번 방문한 적이 있어서 그 주변 탐색이 완료된 정점이라면 패스
                        if(!hasPath[now][nextV] || !hasPath[nextV][now] || visited[nextV]) {
                            continue;
                        }
                        // 방문 q에 넣기 (다음 탐색을 위해)
                        q.add(nextV);
                        visited[nextV] = true;
                        first++;
                    }
                }
                second = n - first;
                return Math.abs(second - first);
            }
        }
        return Integer.MAX_VALUE;
    }
    
    public static void changeFlag(int i, int j, boolean flag) {
       hasPath[i][j] = flag;
        hasPath[j][i] = flag;
    }
}