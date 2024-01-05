import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11657 {

	static int N, M;
	static long[] dist;
	static List<Edge> edgeList = new ArrayList<>();
	static final int INF = 999999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int start, end, weight;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(start, end, weight));
		}

		dist = new long[N + 1];

		Arrays.fill(dist, INF);
		dist[1] = 0;

		boolean ret = bellmanFord();
		
		StringBuilder sb = new StringBuilder();
		if(ret) {
			for (int i = 2; i <= N; i++) {
				sb.append(dist[i] != INF ? dist[i] : -1).append("\n");
			}
		} else {
			sb.append(-1);
		}
		
		System.out.println(sb.toString());
	}

	public static boolean bellmanFord() {
		for (int i = 0; i < N; i++) {

			for (Edge e : edgeList) {

				if (dist[e.start] != INF && dist[e.end] > dist[e.start] + e.weight) {
					dist[e.end] = dist[e.start] + e.weight;
					if (i == N - 1) {
						return false;
					}
				}

			}
		}
		return true;
	}

	public static class Edge {
		int start;
		int end;
		int weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
}
