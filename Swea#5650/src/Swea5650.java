import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea5650 {
	static int[][] map;
	static int ret = Integer.MIN_VALUE;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int endX, endY;

	static int N;
	static int nx, ny;
	static int ndir;
	static boolean flag;
	static int result;
	static int value;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			ret = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					if (map[i][j] == 0) {
						endX = i;
						endY = j;
						for (int dir = 0; dir < 4; dir++) {
							flag = false;
							simulation(i, j, dir, 0,0);
						}
					}

				}
			}

			sb.append(ret).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void simulation(int x, int y, int dir, int cnt, int length) {
		
		nx = x + dx[dir];
		ny = y + dy[dir];
		result = cal(nx, ny, dir);

		if(flag) {
			ret = Math.max(ret, cnt);
			return;
		}
		if (length > 0 && nx == endX && ny == endY) { // 처음 위치에 다시 도착
			ret = Math.max(ret, cnt);
			return;
		}

		simulation(nx, ny, ndir, cnt + result, length+1);
	}

	public static int cal(int x, int y, int dir) {

		if (x >= 0 && x < N && y >= 0 && y < N) { // 일단 경계는 안 넘음

			switch (map[x][y]) {
			case 0:
				ndir = dir;
				return 0;
			case 1:
				if (dir == 2) {
					ndir = 1;
				} else if (dir == 3) {
					ndir = 0;
				} else {
					ndir = (dir + 2) % 4;
				}
				return 1;
			case 2:
				if (dir == 0) {
					ndir = 1;
				} else if (dir == 3) {
					ndir = 2;
				} else {
					ndir = (dir + 2) % 4;
				}
				return 1;
			case 3:
				if (dir == 1) {
					ndir = 2;
				} else if (dir == 0) {
					ndir = 3;
				} else {
					ndir = (dir + 2) % 4;
				}
				return 1;
			case 4:
				if (dir == 1) {
					ndir = 0;
				} else if (dir == 2) {
					ndir = 3;
				} else {
					ndir = (dir + 2) % 4;
				}
				return 1;
			case 5:
				ndir = (dir + 2) % 4;
				return 1;
			case -1 : // 블랙홀
				flag = true;
				return 0;
			default: // 웜홀
				hole(x, y, dir);
				return 0;
			}

		} else { // 밖에 벽에 부딪힘
			ndir = (dir + 2) % 4;
			return 1;
		}

	}

	public static void hole(int x, int y, int dir) {
		value = map[x][y];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!(i == x && j == y) && map[i][j] == value) {
					nx = i;
					ny = j;
					ndir = dir;
					return;
				}
			}
		}
	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y, int depth) {
			this.x = x;
			this.y = y;
		}
	}
}
