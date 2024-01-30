import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10974 {

	static int N;
	static StringBuilder sb = new StringBuilder();
	static int[] pick;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		pick = new int[N + 1];
		visited = new boolean[N + 1];
		permutation(0);
		System.out.println(sb.toString());
	}

	public static void permutation(int cnt) {

		if (cnt == N) {
			for (int i = 0; i < N; i++) {
				sb.append(pick[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			pick[cnt] = i;
			permutation(cnt + 1);
			visited[i] = false;

		}

	}
}
