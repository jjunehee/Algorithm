import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2252 {
	static ArrayList<Integer>[] a;
	static int[] indegree;
	static StringBuilder sb = new StringBuilder();
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		a = new ArrayList[N + 1];
		indegree = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			a[i] = new ArrayList<>();

		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			a[A].add(B);
			indegree[B]++;
		}

		topologySort();
		System.out.println(sb);

	}

	public static void topologySort() {
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}

		for (int i = 0; i < N; i++) {
			int now = q.poll();

			sb.append(now + " ");

			for (int LinkedNode : a[now]) {
				if (--indegree[LinkedNode] == 0) {
					q.add(LinkedNode);
				}
			}
		}
	}

}
