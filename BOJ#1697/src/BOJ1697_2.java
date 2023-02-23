import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697_2 {
	static boolean[] visited = new boolean[100001];
	static int N, K;
	static Queue<Integer> q = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		q.offer(N);
		start(N);
	}

	private static void start(int n) {

		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {

				int cur = q.poll();

				if (cur == K) {
					System.out.println(cnt);
					break;
				}

				if (cur + 1 <= 100000 && !visited[cur + 1]) {
					q.offer(cur + 1);
					visited[cur + 1] = true;
				}
				if (cur - 1 >= 0 && !visited[cur - 1]) {
					q.offer(cur - 1);
					visited[cur - 1] = true;
				}
				if (2 * cur <= 100000 && !visited[cur * 2]) {
					q.offer(2 * cur);
					visited[cur * 2] = true;
				}
			}
			cnt++;
		}
	}

}
