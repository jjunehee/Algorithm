import java.util.*;

class Solution {
    
    static int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        if(n==1) {
            return 0;
        }
        // 크루스칼 (유니온파인드를 활용해 최소신장트리 만들기)
        makeSet(n);
        
        // 간선 세팅
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=0; i<costs.length; i++) {
            pq.add(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }
        
        int connectCnt = 0;
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            
            if(union(e.from, e.to)) {
                answer += e.weight;
                connectCnt++;
                if(connectCnt == n-1) {
                    break;
                } 
            }  
        }
        
        return answer;
    }
    
    // 부모 노드 변수 초기화
    public static void makeSet(int n) {
        parent = new int[n+1];
        for(int i=1; i<=n; i++) {
            parent[i] = i;
        }
    }
    
    public static boolean union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        
        if(p1 == p2) {
            return false;
        }
        
        parent[p2] = p1;
        return true;
    }
    
    public static int find(int v) {
        if(parent[v] != v) {
            parent[v] = find(parent[v]);
        }
        return parent[v];
    }
    
    public static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}