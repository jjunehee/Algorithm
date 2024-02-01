import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1799 {

	static int N;
	static int[][] map;
	static int blackMax = Integer.MIN_VALUE;
	static int whiteMax = Integer.MIN_VALUE;

	static boolean[][] black_visited;
	static boolean[][] white_visited;

	static int[] dx = { -1, -1, 1, 1 };
	static int[] dy = { -1, 1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		black_visited = new boolean[N + 1][N + 1];
		black_search(0, 1, 1);

		white_visited = new boolean[N + 1][N + 1];
		white_search(0, 1, 1);

		System.out.println(blackMax + whiteMax);
	}

	public static void black_search(int cnt, int x, int y) {
		blackMax = Math.max(blackMax, cnt);

		if (y > N) {
			x++;
			y = 1;
		}

		if (x > N) {
			return;
		}

		if (y == 1 && x % 2 == 0) {
			y++;
		}

		if (check(black_visited, x, y)) {
			black_visited[x][y] = true;
			black_search(cnt + 1, x, y + 2);
			black_visited[x][y] = false;
		}

		black_search(cnt, x, y + 2);

	}

	public static void white_search(int cnt, int x, int y) {
		whiteMax = Math.max(whiteMax, cnt);

		if (y > N) {
			x++;
			y = 1;
		}

		if (x > N) {
			return;
		}

		if (y == 1 && x % 2 == 1) {
			y++;
		}

		if (check(white_visited, x, y)) {
			white_visited[x][y] = true;
			white_search(cnt + 1, x, y + 2);
			white_visited[x][y] = false;
		}

		white_search(cnt, x, y + 2);
	}

	public static boolean check(boolean[][] visited, int x, int y) {
		if (map[x][y] == 0) {
			return false;
		}

		for (int dir = 0; dir < 4; dir++) {
			int nx = x;
			int ny = y;
			for (int i = 0; i < N; i++) {
				nx = nx + dx[dir];
				ny = ny + dy[dir];

				if (isBound(nx, ny)) { // 경계일때는 다른 방향으로 가야지
					break;
				}

				if (visited[nx][ny]) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isBound(int nx, int ny) {

		if (nx < 1 || nx > N || ny < 1 || ny > N) {
			return true;
		} else {
			return false;
		}

	}
}
