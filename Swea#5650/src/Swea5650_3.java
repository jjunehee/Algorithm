import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea5650_3 {
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int N;
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
							score = 0;
							simulation(i, j, dir);
							ret = Math.max(ret, score);
						}
					}

				}
			}
			sb.append(ret).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void simulation(int x, int y, int dir) {
		int nx = x;
		int ny = y;
		while (true) {
			nx = nx + dx[dir];
			ny = ny + dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) { // 밖
				dir = (dir + 2) % 4;
				score++;
				continue;
			} else if (map[nx][ny] == -1 || (ny == y) && (nx == x)) {
				break;
			} else if (map[nx][ny] >= 6 && map[nx][ny] <= 10) {
				int value = map[nx][ny];
				outerloop:
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!(i == nx && j == ny) && map[i][j] == value) {
							nx = i;
							ny = j;
							break outerloop;
						}
					}
				}
			} else {
				switch (map[nx][ny]) {
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
				}
			}
		}
	}
}
