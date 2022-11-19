import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution59 {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			map = new int[N + 1][N + 1];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// row check
			int result = 0;
			for (int i = 0; i < N; i++) {
				int count = 0;
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						count++;
						if (map[i][j+1] == 0) {
							if (count == K) {
								result++;
								count = 0;
							}
						}
					} else {
						count = 0;
					}
				}
			}

			for (int j = 0; j < N; j++) {
				int count = 0;
				for (int i = 0; i < N; i++) {
					if (map[i][j] == 1) {
						count++;
						if (map[i + 1][j] == 0) {
							if (count == K) {
								result++;
								count = 0;
							}
						}
					} else {
						count = 0;
					}
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}
