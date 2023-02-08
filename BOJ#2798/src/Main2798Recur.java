import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;public class Main2798Recur {
	static int[] cards;
	static int[] result;
	static int N, M;
	static int sum;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cards = new int[N];
		result = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		recur(0, 0);
		System.out.println(max);
	}

	private static void recur(int cnt, int idx) {
		if (cnt == 3) {
			int sum = 0;
			for (int i = 0; i < result.length; i++) {
				sum += result[i];
			}
			if (sum <= M) {
				max = Math.max(sum, max);
			}
			return;
		}

		for (int i = idx; i < N; i++) {
			result[cnt] = cards[i];
			recur(cnt + 1, i + 1);
		}

	}
}
