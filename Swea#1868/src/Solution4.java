import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution4 {
	static char[][] map;
	static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int N;
	static List<Pos> goodPos;
	static boolean[][] visited;
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
				}
			}

			goodPos = new ArrayList<Pos>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.' && isNotBombAround(i, j)) {
						goodPos.add(new Pos(i, j));
					}
				}
			}

			///////////////////////

			ret = 0;
			for (int i = 0; i < goodPos.size(); i++) {
				Pos now = goodPos.get(i);

				if (!visited[now.x][now.y]) {
					open(now.x, now.y);
					ret++;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.' && !visited[i][j]) {
						ret++;
					}
				}
			}

			sb.append(ret).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void open(int x, int y) {
		visited[x][y] = true;

		int nx, ny;

		boolean flag = true;
		for (int dir = 0; dir < 8; dir++) {
			nx = x + dx[dir];
			ny = y + dy[dir];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			if (map[nx][ny] == '*') {
				flag = false;
				break;
			}
		}
		if (flag) {
			for (int dir = 0; dir < 8; dir++) {
				nx = x + dx[dir];
				ny = y + dy[dir];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
					continue;
				}
				open(nx, ny);
			}
		}
	}

	public static boolean isNotBombAround(int x, int y) {
		int nx, ny;
		for (int dir = 0; dir < 8; dir++) {
			nx = x + dx[dir];
			ny = y + dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}

			if (map[nx][ny] == '*') {
				return false;
			}
		}

		return true;
	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
