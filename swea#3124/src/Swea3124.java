import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Swea3124 {

	static int V;
	static Edge[] edgeList;
	static int[] parents;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			edgeList = new Edge[E];

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
			}

			Arrays.sort(edgeList);

			makeSet();

			int cnt = 0;
            long result = 0;
			for (Edge edge : edgeList) {

				if (union(edge.from, edge.to)) {
					result += edge.weight;
					if (++cnt == V - 1) {
						break;
					}
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean union(int v1, int v2) {
		int parentV1 = findSet(v1);
		int parentV2 = findSet(v2);

		if (parentV1 == parentV2) {
			return false;
		} else {
			parents[parentV1] = parentV2;
			return true;
		}
	}

	private static int findSet(int v) {

		if (v == parents[v]) {
			return v;
		} else {
			return parents[v] = findSet(parents[v]);
		}
	}

	private static void makeSet() {
		parents = new int[V + 1];

		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

	public static class Edge implements Comparable<Edge> {
		int from, to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}
}
