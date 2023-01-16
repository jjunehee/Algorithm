import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2008 {
	static String[][] map;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int maxHeight = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		int N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = st.nextToken();
				}
			}
			
			int height = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					if (map[i][j].equals("B")) {
						height = 1;
						for (int dir = 0; dir < 8; dir++) {
							int nx = i + dx[dir];
							int ny = j + dy[dir];

							if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
								continue;
							}

							if (map[nx][ny].equals("G")) {
								height = 2;
								break;
							} else if (map[nx][ny].equals("B")) {
								height++;
								continue;
							}
						}
					}
					maxHeight = Math.max(maxHeight, height);
				}
			}
			System.out.println(maxHeight);
		}

	}
}
