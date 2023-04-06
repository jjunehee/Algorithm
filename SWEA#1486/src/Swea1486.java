import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1486 {
	static int[] H;
	static int N, B;
	static int min;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			H = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}
			min = Integer.MAX_VALUE;
			search(0, 0);
			sb.append(min-B).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void search(int idx, int sum) {
		if (sum >= B) {
			min = Math.min(min, sum);
			return;
		}

		for (int i = idx; i < N; i++) {
			search(i + 1, sum + H[i]);
		}

	}
}
