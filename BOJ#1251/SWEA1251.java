import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA1251 {

	static int[] xArray;
	static int[] yArray;
	static int N;
	static double E;
	static List<Node> nodeList;
	static PriorityQueue<Edge> edgePQ;
	static int[] pick;
	static boolean[] visited;
	static int[] parent;
	static List<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// TestCase
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			// Amount of Node
			N = Integer.parseInt(br.readLine());

			// input X,y
			xArray = new int[N + 1];
			yArray = new int[N + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				xArray[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				yArray[i] = Integer.parseInt(st.nextToken());
			}

			// input E
			E = Double.parseDouble(br.readLine());

			// Make Node
			makeNode();

			parent = new int[N + 1];
			makeDisjointSet();
			// make Edge
			edgePQ = new PriorityQueue<>();
			pick = new int[2];
			comb(1, 0);

			// 최소 신장트리를 만든다.
			edgeList = new ArrayList<>();
			Kruskal();

			Double ret = calResult();

			sb.append(Math.round(ret)).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static double calResult() {

		double sum = 0;
		for (Edge e : edgeList) {
			sum += E * Math.pow(e.weight, 2);
		}

		return sum;
	}

	public static void makeNode() {
		nodeList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			nodeList.add(new Node(xArray[i], yArray[i]));
		}
	}

	public static void makeDisjointSet() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	public static void comb(int idx, int cnt) {

		if (cnt == 2) {
			int v1 = pick[0];
			int v2 = pick[1];
			double dist = calDist(v1, v2);
			edgePQ.offer(new Edge(v1, v2, dist));

			return;
		}

		for (int i = idx; i <= N; i++) {

			pick[cnt] = i;
			comb(i + 1, cnt + 1);
		}

	}

	public static double calDist(int v1, int v2) {
		Node node1 = nodeList.get(v1 - 1);
		Node node2 = nodeList.get(v2 - 1);
		return Math.sqrt(Math.pow(node1.x - node2.x, 2) + Math.pow(node1.y - node2.y, 2));
	}

	public static void Kruskal() {
		while (!edgePQ.isEmpty()) {
			Edge now = edgePQ.poll();

			if (find(now.start) == find(now.end)) {
				continue;
			}

			union(now.start, now.end);
			edgeList.add(now);
		}
	}

	public static void union(int v1, int v2) {

		int parent_v1 = find(v1);
		int parent_v2 = find(v2);

		if (parent_v1 == parent_v2) {
			return;
		}

		if (v1 <= v2) {
			parent[parent_v1] = parent_v2;
		} else {
			parent[parent_v2] = parent_v1;
		}
	}

	public static int find(int v) {
		if (parent[v] == v) {
			return v;
		}
		return parent[v] = find(parent[v]);
	}

	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Edge implements Comparable<Edge> {
		int start;
		int end;
		double weight;

		public Edge(int start, int end, double weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
}
