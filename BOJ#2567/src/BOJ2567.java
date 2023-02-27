import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2567 {
	static int[][] map;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		map = new int[100][100];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			for (int r = R; r < R + 10; r++) {
				for (int c = C; c < C + 10; c++) {
					map[r][c] = 1;
				}
			}

		}

		int cnt =0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == 1) {
					for (int dir = 0; dir < 4; dir++) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];

						if (nx < 0 || nx >= 100 || ny < 0 || ny >= 100 || map[nx][ny] == 0) {
							cnt++;
							continue;
						}
					}
				}
			}
		}
		System.out.println(cnt);

	}
}