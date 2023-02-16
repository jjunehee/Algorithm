import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea5215 {
	static int[] T;
	static int[] K;
	static int[] pick;
	static int N, L;
	static boolean[] visited;
	static int n;
	static int maxScore;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TT = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= TT; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			T = new int[N];
			K = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				T[i] = Integer.parseInt(st.nextToken());
				K[i] = Integer.parseInt(st.nextToken());
			}

			maxScore = Integer.MIN_VALUE;
			comb(0, 0, 0, 0);

			sb.append(maxScore).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void comb(int cnt, int idx, int sumT, int sumK) {

		if (idx == N) {

			if (sumK <= L) {
				maxScore = Math.max(maxScore, sumT);
			}
			return;

		}

		comb(cnt + 1, idx + 1, sumT + T[idx], sumK + K[idx]);
		comb(cnt, idx + 1, sumT, sumK);

	}
}
