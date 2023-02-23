import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1873 {
	static char[][] map;
	static char[] command;
	static int N;
	static Tank tank;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int H, W;
	static int dir;
	static char[] direction = { '^', '>', 'v', '<' };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);

					if (map[i][j] == '>' || map[i][j] == '<' || map[i][j] == '^' || map[i][j] == 'v') {
						char d = map[i][j];
						if (d == '^') {
							dir = 0;
						} else if (d == '>') {
							dir = 1;
						} else if (d == 'v') {
							dir = 2;
						} else if (d == '<') {
							dir = 3;
						}

						tank = new Tank(i, j, dir);
					}
				}
			}

			N = Integer.parseInt(br.readLine());
			command = new char[N];
			String str = br.readLine();
			for (int i = 0; i < N; i++) {
				command[i] = str.charAt(i);
			}

			gameStart(tank);

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}

		}

		System.out.println(sb.toString());
	}

	public static void gameStart(Tank tank) {

		for (int i = 0; i < N; i++) {

			switch (command[i]) {

			case 'S':
				tank.shoot();
				break;
			case 'U':
				tank.up();
				break;
			case 'D':
				tank.down();
				break;
			case 'L':
				tank.left();
				break;
			case 'R':
				tank.right();
				break;

			}
		}

	}

	public static class Tank {
		int x;
		int y;
		int dir;

		public Tank(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		void shoot() {

			int bulletX = this.x;
			int bulletY = this.y;

			while (true) {
				bulletX = bulletX + dx[dir];
				bulletY = bulletY + dy[dir];

				// 맵 밖으로 나갔다면 아무런 변화없이 끝
				if (bulletX < 0 || bulletX >= H || bulletY < 0 || bulletY >= W) {
					break;
				}

				// 벽돌로 만들어진 벽이라면 벽 부시고 평지로 만든다.
				if (map[bulletX][bulletY] == '*') {
					map[bulletX][bulletY] = '.';
					break;
				}

				// 강철로 만들어진 벽이라면 팅~
				if (map[bulletX][bulletY] == '#') {
					break;
				}
			}

		}

		void up() {
			move(0);
		}

		void right() {
			move(1);
		}

		void left() {
			move(3);
		}

		void down() {
			move(2);
		}

		void move(int dir) {
			int nx = this.x + dx[dir];
			int ny = this.y + dy[dir];
			map[this.x][this.y] = direction[dir];
			this.dir = dir;

			if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
				return;
			}
			if (map[nx][ny] == '-' || map[nx][ny] == '*' || map[nx][ny] == '#') {
				return;
			}

			map[this.x][this.y] = '.';
			map[nx][ny] = direction[dir];
			this.x = nx;
			this.y = ny;
		}
	}
}
