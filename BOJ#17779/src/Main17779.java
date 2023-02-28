import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 게리맨더링2
public class Main17779 {
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		// 완탐
		// 어떻게 구역을 나눠줄까.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {

						if (x + d1 + d2 > N)
							continue;
						if (y - d1 < 1 || y + d2 > N)
							continue;

						solution(x, y, d1, d2);
					}
				}
			}
		}

	}

	private static void solution(int x, int y, int d1, int d2) {
		
		
	}
}
