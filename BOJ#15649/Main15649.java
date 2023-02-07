import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15649 {
	static int[] result;
	static boolean[] visited;
	static int N, M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		result = new int[M];

		perm(0);
		System.out.println(sb.toString());

	}

	private static void perm(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < result.length; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i] == true) {
				continue;
			}

			visited[i] = true;
			result[cnt] = i;
			perm(cnt + 1);
			visited[i] = false;
		}
	}
}
