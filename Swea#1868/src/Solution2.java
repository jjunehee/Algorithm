import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
	static char[][] map;
	static int[][] mCnt;
	static boolean[][] visited;
	static int N;
	static int ret;

	static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			ret = 0;
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			mCnt = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
				}
			}

			mineCount();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && mCnt[i][j] == 0 && map[i][j] == '.') {
						click(i, j);
						ret++;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && mCnt[i][j] > 0 && map[i][j] == '.') {
						ret++;
					}
				}
			}

			sb.append(ret).append("\n");

		}

		System.out.println(sb.toString());
	}

	private static void mineCount() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int count = 0;
				int nx, ny;
				for (int dir = 0; dir < 8; dir++) {
					nx = i + dx[dir];
					ny = j + dy[dir];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}

					if (map[nx][ny] == '*') {
						count++;
					}
				}
				mCnt[i][j] = count;
			}
		}

	}

	public static void click(int x, int y) {

		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(x, y));

		while (!q.isEmpty()) {
			Pos cur = q.poll();
			visited[cur.x][cur.y] = true;

			int nx, ny;
			for (int dir = 0; dir < 8; dir++) {

				nx = cur.x + dx[dir];
				ny = cur.y + dy[dir];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] != '*') {
					visited[nx][ny] = true;
					if(mCnt[nx][ny] == 0) {
						q.add(new Pos(nx,ny));
					}
				}
			}

		}

	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
