import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {
	static boolean[] visited = new boolean[100001];
	static int N, K;
	static Queue<info> q = new ArrayDeque<>();
	static int depth;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		q.offer(new info(N, 0));
		start(N);
		System.out.println(min);
	}

	private static void start(int n) {

		while (!q.isEmpty()) {
			info cur = q.poll();

			if (cur.num == K) {
				min = cur.depth;
				break;
			}

			if (cur.num + 1 <= 100000 && !visited[cur.num + 1]) {
				q.offer(new info(cur.num + 1, cur.depth + 1));
				visited[cur.num + 1] = true;
			}
			if (cur.num - 1 >= 0 && !visited[cur.num - 1]) {
				q.offer(new info(cur.num - 1, cur.depth + 1));
				visited[cur.num - 1] = true;
			}
			if (2 * cur.num <= 100000 && !visited[cur.num * 2]) {
				q.offer(new info(2 * cur.num, cur.depth + 1));
				visited[cur.num * 2] = true;
			}
		}
	}

	public static class info {
		int num;
		int depth;

		public info(int num, int depth) {
			this.num = num;
			this.depth = depth;
		}
	}

}
