import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11403 {

	static int N;
	static List<Integer>[] info;
	static int[][] ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		ret = new int[N + 1][N + 1];

		info = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			info[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					info[i].add(j);
				}
			}
		}

		solution();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(ret[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void solution() {
		for (int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[N+1];

			Queue<Integer> q = new LinkedList<>();
			for (int v : info[i]) {
				ret[i][v] = 1;
				visited[v] = true;
				q.add(v);
			}

			
			while (!q.isEmpty()) {
				int v = q.poll();

				for (int v2 : info[v]) {
					if(visited[v2]) {
						continue;
					}
					ret[i][v2] = 1;
					visited[v2] = true;
					q.add(v2);
				}
			}
		}
	}
}
