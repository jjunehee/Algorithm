import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ5972 {

	static List<Edge>[] graphInfo; // 그래프 정보
	static int N, M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graphInfo = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			graphInfo[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graphInfo[A].add(new Edge(B, C));
			graphInfo[B].add(new Edge(A, C));
		}

		System.out.println(solution());
	}

	public static int solution() {

		// 거리테이블 생성, 초기
		int[] dist = new int[N + 1]; 
		Arrays.fill(dist, Integer.MAX_VALUE);

		// 간선 정보 담을 우선순위
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// 출발지점 초기화
		dist[1] = 0;
		pq.offer(new Edge(1, 0));

		while (!pq.isEmpty()) {
			
			// 인접한 노드중에서 가중치가 가장 작은 노드로의 간선
			Edge e = pq.poll();

			// 현재 정점을 거쳐서 e.to로 가는 것보다 전에 다른 루트로 도착해 표시해둔 최단거리가 더 짧다면 continue
			if (e.weight > dist[e.to]) {
				continue;
			}

			for (Edge next : graphInfo[e.to]) { // e.to와 연결된 간선 탐색
				if (dist[next.to] > e.weight + next.weight) { // 해당 간선으로 가는 정점이 기존 최단거리보다 짧다면
					dist[next.to] = e.weight + next.weight; // 그 정점에 대한 최단 거리 값 변경
					pq.offer(new Edge(next.to, dist[next.to])); // 그리고 그 간선정보를 우선순위큐에 삽입!
				}
			}
		}

		return dist[N]; // 시작정점에서 N(도착 정점)에 도달하는 최단거리 값.

	}

	public static class Edge implements Comparable<Edge> {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
