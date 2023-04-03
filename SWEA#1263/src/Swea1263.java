import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1263 {
	static int[][] D;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			D = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					D[i][j] = Integer.parseInt(st.nextToken());
					if (i == j) {
						D[i][j] = 0;
					} else if (D[i][j] == 0) {
						D[i][j] = 5000;
					}
				}
			}

			for (int k = 1; k <= N; k++) { // k 경유지

				for (int i = 1; i <= N; i++) { // i 출발지

					for (int j = 1; j <= N; j++) { // j 도착지

						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}

			int min = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int j = 1; j <= N; j++) {
					sum += D[i][j];
				}
				min = Math.min(min, sum);
			}
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
