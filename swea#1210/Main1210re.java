import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1210re {
	static int[][] map;
	static int answer;
	static int[] dx = { 0, 0, -1 };
	static int[] dy = { -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		for (int T = 1; T <= 10; T++) {
			sb.append("#" + T + " ");
			int t = Integer.parseInt(br.readLine());
			map = new int[100][100];
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int j = 0; j < 100; j++) {
				if (map[99][j] == 2) {
					Solution(99, j);
					sb.append(answer).append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}

	private static void Solution(int x, int y) {

		if (x == 0) {
			answer = y;
			return;
		}

		map[x][y] = 3;

		for (int dir = 0; dir < 3; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx >= 0 && nx < 100 && ny >= 0 && ny < 100 && map[nx][ny] == 1) {
				Solution(nx, ny);
				break;
			}
		}

	}
}
