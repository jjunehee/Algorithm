import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5214 {

	static int N, K, M;
	static int[][] info;
	static boolean[][] graphInfo;
	static int[] pick = new int[2];
	static int ret = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		info = new int[M+1][K];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				int num = Integer.parseInt(st.nextToken());
				info[i][j] = num;
			}
		}

		graphInfo = new boolean[N + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			combination(i, 0, 0);
		}

		System.out.println(solution());
		
	}

	public static int solution() {

		Queue<checkPoint> q = new LinkedList<>();
		q.add(new checkPoint(1,1));
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		while (!q.isEmpty()) {
			checkPoint now = q.poll();
			if(now.v == N) {
				ret = now.dist;
				break;
			}
			for (int i = 1; i <= N; i++) {
				if (graphInfo[now.v][i]) {
					if (visited[i]) {
						continue;
					}
					visited[i] = true;
					q.add(new checkPoint(i, now.dist + 1));
				}
			}
		}
		
		if(ret == Integer.MAX_VALUE) {
			return -1;
		} else {
			return ret;
		}
	}

	public static void combination(int m, int idx, int cnt) {

		if (cnt == 2) {
			graphInfo[pick[0]][pick[1]] = true;
			graphInfo[pick[1]][pick[0]] = true;
			return;
		}

		for (int i = idx; i < K; i++) {
			pick[cnt] = info[m][i];
			combination(m, i + 1, cnt + 1);
		}
	}

	public static class checkPoint {
		int v;
		int dist;

		public checkPoint(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}
	}
}
