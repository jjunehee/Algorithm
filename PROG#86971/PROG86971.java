

// 9, [[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]]

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PROG86971 {

    static int[] parent;
    static boolean[][] hasPath;
    static List<Integer>[] connectInfo;

    public static void main(String[] args) {

        int[][] wires = {{1,3},{2,3}, {3,4}, {4,5}, {4,7}, {7,8}, {7,9}};
           
    
        System.out.println(solve(9, wires));
    }

    public static int solve(int n, int[][] wires) {

        connectInfo = new List[n+1];
        for(int v=1; v<=n; v++) {
            connectInfo[v] = new ArrayList<>();
        }

        // 간선 여부 초기화
        hasPath = new boolean[n+1][n+1];
        for(int i=0; i<wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            hasPath[v1][v2] = true;
            hasPath[v2][v1] = true;
            connectInfo[v1].add(v2);
            connectInfo[v2].add(v1);
        
        }

        int minGap = Integer.MAX_VALUE;
        for(int i=0; i<wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            if(hasPath[v1][v2]) {
                hasPath[v1][v2] = false;
                hasPath[v2][v1] = false;
                System.out.println(v1 + " " + v2 +  "끊기");
                minGap = Math.min(minGap, findGap(n));
                hasPath[v1][v2] = true;
                hasPath[v2][v1] = true;
                System.out.println("갭차이 :" +minGap);
            }
            
        }
        
        return minGap;
    
    }

    public static int findGap(int n) {

        boolean[] visited = new boolean[n+1];

        // 정점들을 전체적으로 둘러본다.
        int cnt = 0;
        for(int v=1; v<=n; v++) {

            int check = 0;
            // 방문하지 않았던 정점부터 너비우선탐색 진행
            if(!visited[v]) {
                Queue<Integer> q = new LinkedList<>();
                q.add(v); // 방문 q에 넣고
                visited[v] = true; // 방문 여부 체크
                cnt++;
            
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
                        cnt++;
                    }
                }
                if(check == 0) {
                    check = cnt;
                } else {
                    return Math.abs(check - cnt);
                }
            }
        }

        return Integer.MAX_VALUE;

    }

}