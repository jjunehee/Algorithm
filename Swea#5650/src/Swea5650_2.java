import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea5650_2 {
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int N;
	static int x, y, dir;
	static int score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ret = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						for (int dir = 0; dir < 4; dir++) {
							map[i][j] = -1; // 게임오버 하는 곳 -1로 통일
							score = 0;
							simulation(i, j, dir);
							ret = Math.max(ret, score);
							map[i][j] = 0;
						}
					}
				}
			}
			sb.append(ret - 1).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void simulation(int x, int y, int dir) {
		int nx, ny;
		while (true) {

			while (true) { // 직진 계에속
				nx = x + dx[dir];
				ny = y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 0) {
					x = nx;
					y = ny;
					break;
				}
				x = nx;
				y = ny;
			}

			// 이상한 곳 도착

			if (x < 0 || x >= N || y < 0 || y >= N) { // 밖
				dir = (dir + 2) % 4;
				score++;
			} else if (map[x][y] != -1) {
				switch (map[x][y]) {
				case 1:
					if (dir == 2) {
						dir = 1;
					} else if (dir == 3) {
						dir = 0;
					} else {
						dir = (dir + 2) % 4;
					}
					score++;
					break;
				case 2:
					if (dir == 0) {
						dir = 1;
					} else if (dir == 3) {
						dir = 2;
					} else {
						dir = (dir + 2) % 4;
					}
					score++;
					break;
				case 3:
					if (dir == 1) {
						dir = 2;
					} else if (dir == 0) {
						dir = 3;
					} else {
						dir = (dir + 2) % 4;
					}
					score++;
					break;
				case 4:
					if (dir == 1) {
						dir = 0;
					} else if (dir == 2) {
						dir = 3;
					} else {
						dir = (dir + 2) % 4;
					}
					score++;
					break;
				case 5:
					dir = (dir + 2) % 4;
					score++;
					break;
				default: // 웜홀
					int value = map[x][y];
					outerloop: for (int i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							if (!(i == x && j == y) && map[i][j] == value) {
								x = i;
								y = j;
								break outerloop;
							}
						}
					}
				}
			} else {
				break;
			}
		}

	}

}
