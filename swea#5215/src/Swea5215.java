import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea5215 {
	static int[] T;
	static int[] K;
	static int[] pick;
	static int N;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TT = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= TT; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			T = new int[N];
			K = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				T[i] = Integer.parseInt(st.nextToken());
				K[i] = Integer.parseInt(st.nextToken());
			}

			pick = new int[N];
			visited = new boolean[N];
			comb(0, 0);

		}
	}

	private static void comb(int cnt, int idx) {

		if (cnt == N) {
			for (int i = 0; i < N; i++) {
				System.out.print(visited[i] + " ");
			}
		}

		for (int i = idx; i < N; i++) {
			if (visited[i])
				continue;
			pick[i] = i;
			visited[i] = true;
			comb(cnt + 1, i + 1);
			visited[i] = false;
		}
	}
}
