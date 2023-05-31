import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class aProblemTopology {

	static int N;
	static int[] orgInDegree, times, inDegree, D;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());

			orgInDegree = new int[N + 1];
			times = new int[N + 1];
			inDegree = new int[N + 1];
			D = new int[N + 1];
			adjList = new ArrayList[N + 1];

			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<>();
			}

			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				times[i] = Integer.parseInt(st.nextToken());
				cnt = Integer.parseInt(st.nextToken());
				orgInDegree[i] = cnt;

				for (int c = 1; c <= cnt; c++) {
					adjList[Integer.parseInt(st.nextToken())].add(i);
				}
			}
			sb.append(solve()).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static int solve() {

		int min = Integer.MAX_VALUE;
		int temp, res;
		for (int i = 1; i <= N; i++) {
			temp = times[i];
			times[i] = times[i] / 2;
			res = getTime();
			if (res == -1)
				return -1;
			min = Math.min(min, res);
			times[i] = temp;
		}
		return min;

	}

	public static int getTime() {

		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			D[i] = 0;
			inDegree[i] = orgInDegree[i];
			if (inDegree[i] == 0) {
				q.add(i);
			}
		}

		int max = 0;
		int cnt = 0;
		while (!q.isEmpty()) {
			cnt++;
			int cur = q.poll();
			D[cur] += times[cur];
			max = Math.max(max, D[cur]);

			for (int to : adjList[cur]) {
				D[to] = Math.max(D[to], D[cur]);
				if (--inDegree[to] == 0) {
					q.add(to);
				}
			}
		}
		return cnt == N ? max : -1;
	}
}
