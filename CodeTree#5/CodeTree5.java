import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree5 {

	static int N, M, K;

	static int[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num > 0) { // 벽
					map[i][j] = -num;
				}
			}
		}

		int x, y;
		for (int i = 0; i < M; i++) { // 사람
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y]++;
		}

		st = new StringTokenizer(br.readLine());
		// 출구
		x = Integer.parseInt(st.nextToken()) - 1;
		y = Integer.parseInt(st.nextToken()) - 1;
		map[x][y] = -10;

		simulation();
		System.out.println(result);
		Pos exit = findExit();

		System.out.println((exit.x + 1) + " " + (exit.y + 1));

	}

	public static Pos findExit() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == -10) {
					return new Pos(i, j);
				}
			}
		}
		return null;
	}

	public static void simulation() {

		int time = 0;
		while (time < K) {

//			printMap();
			move();
//			printMap();
			rotate();
//			printMap();

			time++;
		}

	}

	public static void move() {

		int[][] newMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (map[i][j] > 0) { // 사람

					int nx, ny;
					for (int dir = 0; dir < 4; dir++) {
						nx = i + dx[dir];
						ny = j + dy[dir];

						if (!checkBound(nx, ny) && (map[nx][ny] >= 0 || map[nx][ny] == -10)
								&& checkDist(i, j, nx, ny)) { // 그 곳이 빈칸 or 사람이 있고
							Pos exit = findExit();
							result += map[i][j];
							if (exit.x == nx && exit.y == ny) { // 이동한 그 곳이 도착지
								newMap[i][j] = 0;
								break;
							}
							newMap[nx][ny] += map[i][j];
							newMap[i][j] = 0;
							break;
						}
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = newMap[i][j];
			}
		}

	}

	public static boolean checkBound(int nx, int ny) {

		if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return true;
		} else {
			return false;
		}
	}

	public static void rotate() {

		// 정사각형 찾기
		outerloop: for (int size = 2; size <= N; size++) {
			for (int i = 0; i <= N - size; i++) {
				for (int j = 0; j <= N - size; j++) {
					if (test(i, j, size)) {
						rotateRec(i, j, size);
						break outerloop;
					}
				}
			}
		}

	}

	public static boolean test(int x, int y, int size) {

		boolean isPerson = false;
		boolean isExit = false;

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j] == -10) {
					isExit = true;
				}

				if (map[i][j] > 0) {
					isPerson = true;
				}
			}
		}

		if (isExit && isPerson) {
			return true;
		} else {
			return false;
		}
	}

	public static void rotateRec(int x, int y, int size) {

		Queue<Integer> q = new LinkedList<>();

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				q.add(map[i][j]);
			}
		}

		for (int j = y + size - 1; j >= y; j--) {
			for (int i = x; i < x + size; i++) {
				map[i][j] = q.poll();
				if (map[i][j] != -10 && map[i][j] < 0) {
					map[i][j]++;
				}
			}
		}

	}

	public static boolean checkDist(int x, int y, int nx, int ny) {
		Pos exit = findExit();

		int curDist = Math.abs(exit.x - x) + Math.abs(exit.y - y);

		int nextDist = Math.abs(exit.x - nx) + Math.abs(exit.y - ny);

		if (nextDist < curDist) {
			return true;
		} else {
			return false;
		}
	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void printMap() {
		System.out.println("=============");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}