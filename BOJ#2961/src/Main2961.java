import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2961 {
	static int[] S;
	static int[] B;
	static int N;
	static long min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		S = new int[N];
		B = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		if (N == 1) {
			min = Math.abs(S[0] - B[0]);
		} else {
			recur(0, 0, 1, 0);
		}
		System.out.println(min);
	}

	private static void recur(int cnt, int idx, int Ssum, int Bsum) {
		if (cnt == N && Bsum != 0) {

			min = Math.min(min, Math.abs(Ssum - Bsum));
			return;
		}

		if (idx == N) {
			return;
		}
		recur(cnt + 1, idx + 1, Ssum * S[idx], Bsum + B[idx]);
		recur(cnt + 1, idx + 1, Ssum, Bsum);
	}
}
