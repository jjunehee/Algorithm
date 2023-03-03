import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144 {
	static int[][] map;
	static Robot robot;
	static int[][] changeDust;
	static int R, C;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static boolean flag;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (!flag && map[i][j] == -1) {
					robot = new Robot(i, i + 1);
					flag = true;
				}
			}
		}

		int time = 1;
		while (time <= T) {
			// 미세먼지가 주변에 퍼진다.
			SpreadDust();

			// 공기청정기가 작동한다.
			robot.up();
			robot.down();

			time++;
		}

		int ret = checkDust();

		System.out.println(ret);
	}

	private static void SpreadDust() {
		int dust;
		changeDust = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0) { // 먼지라면
					dust = map[i][j] / 5;
					int cnt = spreadAround(i, j, dust);
					map[i][j] -= (dust * cnt);
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += changeDust[i][j];
			}
		}
	}

	private static int spreadAround(int x, int y, int dust) {

		int cnt = 0;
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) { // 범위를 넘어갔거나 공기청정기라면 안퍼짐
				continue;
			}
			changeDust[nx][ny] += dust;
			cnt++;
		}

		return cnt;

	}

	private static int checkDust() {
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					sum += map[i][j];
				}
			}
		}

		return sum;
	}

	public static class Robot {
		int up;
		int down;

		public Robot(int up, int down) {
			this.up = up;
			this.down = down;
		}

		void up() {
			int dir = 2;
			int dirCount = 0;

			int nowX = this.up;
			int nowY = 0;
			while (true) {
				int nx = nowX + dx[dir];
				int ny = nowY + dy[dir];
				if (nx < 0 || nx > this.up || ny < 0 || ny >= C) {
					dir = (dir + 1) % 4;
					dirCount++;
					if (dirCount == 4) {
						break;
					}
					continue;
				}

				map[nowX][nowY] = map[nx][ny];

				nowX = nx;
				nowY = ny;
			}
			map[this.up][1] = 0;
			map[this.up][0] = 0;
		}

		void down() {
			int dir = 0;
			int dirCount = 0;
			int nowX = this.down;
			int nowY = 0;
			while (true) {
				int nx = nowX + dx[dir];
				int ny = nowY + dy[dir];

				if (nx < this.down || nx >= R || ny < 0 || ny >= C) {
					dir = (dir + 3) % 4;
					dirCount++;
					if (dirCount == 4) {
						break;
					}
					continue;
				}
				map[nowX][nowY] = map[nx][ny];

				nowX = nx;
				nowY = ny;
			}
			map[this.down][1] = 0;
			map[this.down][0] = 0;
		}
	}
}
