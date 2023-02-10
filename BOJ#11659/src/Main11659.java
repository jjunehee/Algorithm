import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11659 {
	static int[] sums;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		sums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			sums[n] = Integer.parseInt(st.nextToken()) + sums[n - 1];
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			sb.append(sums[j] - sums[i - 1]).append("\n");
		}

		System.out.println(sb.toString());

	}
}
