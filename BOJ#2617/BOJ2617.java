import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2617 {

	static int N, M;
	static List<Integer>[] infoA;
	static List<Integer>[] infoB;
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		infoA = new ArrayList[N + 1];
		infoB = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			infoA[i] = new ArrayList<>();
			infoB[i] = new ArrayList<>();
		}

		int v1, v2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			infoA[v1].add(v2);
			infoB[v2].add(v1);
		}

		System.out.println(solution());

	}

	public static int solution() {

		boolean[] visited;
		Queue<Integer> q;
		for (int i = 1; i <= N; i++) {

			q = new LinkedList<>();
			q.add(i);

			visited = new boolean[N + 1];
			visited[i] = true;

			int cnt = 0;
			boolean flag = false;
			outerloop: while (!q.isEmpty()) {
				int v = q.poll();
				for (int v2 : infoA[v]) {
					if (visited[v2]) {
						continue;
					}
					cnt++;
					if (cnt >= (N + 1) / 2) {
						flag = true;
						break outerloop;
					}
					visited[v2] = true;
					q.add(v2);
				}
			}

			if (!flag) {
				q = new LinkedList<>();
				q.add(i);

				visited = new boolean[N + 1];
				visited[i] = true;
				cnt = 0;
				outerloop: while (!q.isEmpty()) {
					int v = q.poll();
					for (int v2 : infoB[v]) {
						if (visited[v2]) {
							continue;
						}
						cnt++;
						if (cnt >= (N + 1) / 2) {
							flag = true;
							break outerloop;
						}
						visited[v2] = true;
						q.add(v2);
					}
				}
			}

			if (flag) {
				ret++;
			}
		}

		return ret;
	}
}
