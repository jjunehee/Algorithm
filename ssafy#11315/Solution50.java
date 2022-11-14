import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution50 {
	static char[][] map;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static String[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new String[T];
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
				}
			}

			boolean fivemok = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 'o') {

						for (int dir = 0; dir < 8; dir++) {
							int nx = i;
							int ny = j;
							int count = 1;
							for (int k = 0; k < 4; k++) {
								nx = nx + dx[dir];
								ny = ny + dy[dir];

								if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
									break;
								}

								if (map[nx][ny] == 'o') {
									count++;
									if (count == 5) {
										fivemok = true;
									}
									continue;
								}
								break;
							}
						}
					}
				}
			}

			answer[t] = fivemok == true ? "YES" : "NO";
		}
		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}
}
