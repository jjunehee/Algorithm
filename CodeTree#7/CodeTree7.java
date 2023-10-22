import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CodeTree7 {

	static int L, N, Q;
	static int[][] map;
	static int[][] tmp;
	static boolean[][] isTrap;
	static boolean[][] isWall;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static List<Command> commandList = new ArrayList<>();
	static List<Robot> robotList = new ArrayList<>();
	static int result;
	static boolean[] ccheck;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		map = new int[L][L];
		isTrap = new boolean[L][L];
		isWall = new boolean[L][L];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < L; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					isTrap[i][j] = true;
				} else if (num == 2) {
					isWall[i][j] = true;
				}
			}
		}

		int r, c, h, w, k;
		robotList.add(new Robot(-1, -1, 0));
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			for (int x = r; x < r + h; x++) {
				for (int y = c; y < c + w; y++) {
					map[x][y] = i;
				}
			}
			robotList.add(new Robot(i, k, 0));
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			commandList.add(new Command(num, dir));
		}

		int result = simulation();
		System.out.println(result);
	}

	public static int simulation() {

		for (int i = 0; i < commandList.size(); i++) {
			Command now = commandList.get(i);

			if (!canMove(now.num, now.dir)) {
				continue;
			}

			tmp = new int[L][L];
			

			for (int a = 0; a < L; a++) {
				for (int b = 0; b < L; b++) {
					tmp[a][b] = map[a][b];
				}
			}
			
			ccheck = new boolean[N+1];
			move(now.num, now.dir);
			tmpToMap();
			damage(now.num);
			
		}

		return check();
	}

	public static void tmpToMap() {
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	public static int check() {

		int sum = 0;
		boolean[] check = new boolean[N + 1];
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				if (map[i][j] > 0 && !check[map[i][j]]) {
					Robot robot = robotList.get(map[i][j]);
					sum += robot.damage;
					check[map[i][j]] = true;
				}
			}
		}

		return sum;
	}

	public static void damage(int num) {

		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				if (map[i][j] > 0 && map[i][j] != num && ccheck[map[i][j]]) {
					if (isTrap[i][j]) {
						Robot robot = robotList.get(map[i][j]);
						robot.damage += 1;
						if (robot.durability == robot.damage) {
							remove(map[i][j]);
						}
					}
				}
			}
		}
	}

	public static void remove(int num) {
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				if (map[i][j] == num) {
					map[i][j] = 0;
				}
			}
		}
	}

	public static boolean canMove(int num, int dir) {

		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				if (map[i][j] == num) {
					int nx, ny;
					nx = i + dx[dir];
					ny = j + dy[dir];

					// 움직여야할 곳에 벽이 존재
					if (isBound(nx, ny)) {
						return false;
					}

					// 벽이 존재
					if (isWall[nx][ny]) {
						return false;
					}

					// 나의 일부분
					if (map[nx][ny] == num) {
						continue;
					}

					if (map[nx][ny] == 0) {
						continue;
					}

					if (map[nx][ny] != num) {
						if (!canMove(map[nx][ny], dir)) {
							return false;
						}
					}

				}
			}
		}

		return true;
	}

	public static void move(int num, int dir) {

		boolean[][] check = new boolean[L][L];
		ccheck[num] = true;
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				if (map[i][j] == num) {

					int nx, ny;
					nx = i + dx[dir];
					ny = j + dy[dir];
					if (map[nx][ny] == 0 || map[nx][ny] == num) {
						tmp[nx][ny] = num;
						check[nx][ny] = true;
						if (!check[i][j]) {
							tmp[i][j] = 0;
						}
						continue;
					}

					move(map[nx][ny], dir);
					tmp[nx][ny] = num;
					check[nx][ny] = true;
					if (!check[i][j]) {
						tmp[i][j] = 0;
					}

				}
			}
		}
	}

	public static boolean isBound(int nx, int ny) {

		if (nx < 0 || nx >= L || ny < 0 || ny >= L) {
			return true;
		}
		return false;
	}

	public static class Robot {
		int num;
		int durability;
		int damage;

		public Robot(int num, int durability, int damage) {
			this.num = num;
			this.durability = durability;
			this.damage = damage;
		}
	}

	public static class Command {
		int num;
		int dir;

		public Command(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
}
