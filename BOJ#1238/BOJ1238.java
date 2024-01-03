import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238 {

	static int N, M, X;
	static List<Edge>[] go;
	static List<Edge>[] back;
	static int[] sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		go = new ArrayList[N + 1];
		back = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			go[i] = new ArrayList<>();
			back[i] = new ArrayList<>();
		}

		int v1, v2, weight;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			go[v1].add(new Edge(v2, weight));
			back[v2].add(new Edge(v1, weight));
		}

		sum = new int[N + 1];

		solution(go, X);
		solution(back, X);
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, sum[i]);
		}

		System.out.println(max);

	}

	public static void solution(List<Edge>[] info, int start) {

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		visited[start] = true;
		dist[start] = 0;

		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (dist[e.v] < e.weight) {
				continue;
			}

			for (Edge next : info[e.v]) {
				if (dist[next.v] > e.weight + next.weight) {
					dist[next.v] = e.weight + next.weight;
					pq.offer(new Edge(next.v, dist[next.v]));
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			sum[i] += dist[i];
		}

	}

	public static class Edge implements Comparable<Edge> {

		int v;
		int weight;

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
