import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class aProblem2 {

	static int N;
	static int[] subway;
	static int[] pick;
	static int max;

	public static void main(String[] args) throws IOException {

		// 다뽑고
		// 조건에 맞춰 걸러주자
		// nC4
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());

			subway = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				subway[i] = Integer.parseInt(st.nextToken());
			}

			max = 0;
			pick = new int[4];
			comb(0, 0);

			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());

	}

	public static void comb(int idx, int cnt) {
		if (cnt == 4) {
			if (available(pick)) {

				int scoreA = (int) Math.pow(subway[pick[0]] + subway[pick[1]], 2);
				int scoreB = (int) Math.pow(subway[pick[2]] + subway[pick[3]], 2);

				max = Math.max(max, scoreA + scoreB);

				scoreA = (int) Math.pow(subway[pick[0]] + subway[pick[3]], 2);
				scoreB = (int) Math.pow(subway[pick[1]] + subway[pick[2]], 2);

				max = Math.max(max, scoreA + scoreB);

			}
			return;
		}

		for (int i = idx; i < N; i++) {
			pick[cnt] = i;
			comb(i + 1, cnt + 1);
		}
	}

	public static boolean available(int[] pick) {

		for (int i = 0; i < 3; i++) {
			if (pick[i + 1] - pick[i] == 1)
				return false;
		}
		if (pick[3] - pick[0] == N - 1) {
			return false;
		}

		return true;

	}
}
