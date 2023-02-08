import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2798For {
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

		for (int i = 0; i < N; i++) {
			result[0] = cards[i];
			for (int j = i + 1; j < N; j++) {
				result[1] = cards[j];
				for (int k = j + 1; k < N; k++) {
					result[2] = cards[k];
					int sum = 0;
					for (int t = 0; t < result.length; t++) {
						sum += result[t];
					}
					if (sum <= M) {
						max = Math.max(sum, max);
					}
				}
			}
		}
		System.out.println(max);

	}
}
