import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {

	static int N, M, V;
	static List<Integer>[] graphInfo;
	static StringBuffer sb = new StringBuffer();
	static boolean[] visited;

	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		graphInfo = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graphInfo[i] = new ArrayList<>();
		}

		int v1, v2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());

			graphInfo[v1].add(v2);
			graphInfo[v2].add(v1);
		}

		visited = new boolean[N + 1];
		DFS(V);
		sb.append("\n");
		visited = new boolean[N + 1];
		BFS(V);
		System.out.println(sb.toString());
	}

	private static void DFS(int v) {
		visited[v] = true;
		sb.append(v + " ");

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < graphInfo[v].size(); i++) {
			int next = graphInfo[v].get(i);
			pq.add(next);
		}

		while (!pq.isEmpty()) {
			int num = pq.poll();
			if (visited[num]) {
				continue;
			}
			DFS(num);
		}

	}

	private static void BFS(int v) {
		visited[v] = true;
		sb.append(v + " ");

		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		while (!q.isEmpty()) {

			int now = q.poll();

			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int i = 0; i < graphInfo[now].size(); i++) {
				int next = graphInfo[now].get(i);
				pq.add(next);
			}

			while (!pq.isEmpty()) {
				int num = pq.poll();
				if (visited[num]) {
					continue;
				}

				visited[num] = true;
				sb.append(num + " ");
				q.add(num);
			}

		}
	}
}
