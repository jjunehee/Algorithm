import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree8 {

	static int N, M, K;

	static int[][] map;
	static Person[] pArray;
	static Pos exit;
	static int moveRet;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int check = 1;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];

		int x, y;
		pArray = new Person[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			pArray[i] = new Person(new Pos(x, y), -1, false);
			map[x][y] = -1;
		}

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		exit = new Pos(x, y);
		map[x][y] = -9999;

		check = M;
		lego();

		System.out.println(moveRet);
		System.out.println(exit.x + " " + exit.y);
	}

	public static void lego() {
		int time = 0;
		while (time < K) {
			// 움직인다.
			move();
			// 회전한다.
			rotate();
			time++;
		}

	}

	public static void move() {

		// 한 사람씩 움직일 수 있는지 확인 후 움직이기!
		for (int i = 1; i <= check; i++) {

			Person person = pArray[i];

			int nx, ny;
			// 상하좌우 check~!
			for (int dir = 0; dir < 4; dir++) {
				nx = person.pos.x + dx[dir];
				ny = person.pos.y + dy[dir];

				// 이동할 곳이 경계를 벗어났거나 벽이 있으면 못가지~~
				if (isBoundOrWall(nx, ny)) {
					continue;
				}

				// 일단 이동을 할 수는 있는데, 출구와의 거리가 가까워져야함.
				if (checkDist(person.pos.x, person.pos.y, nx, ny)) {
					// 이제 진짜 이동
					moveRet += Math.abs(map[person.pos.x][person.pos.y]);
					pArray[i].pos.x = nx;
					pArray[i].pos.y = ny;
					if (nx == exit.x && ny == exit.y) {
						pArray[i].exit = true;
					}
					break;
				}

			}

		}
		drawMap();

	}

	public static void drawMap() {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] < 0 && map[i][j] != -9999) {
					map[i][j] = 0;
				}
			}
		}

		for (int i = 1; i <= check; i++) {

			Person person = pArray[i];
			if (person.exit) {
				continue;
			}
			map[person.pos.x][person.pos.y] += person.value;

		}

		map[exit.x][exit.y] = -9999;
	}

	public static boolean checkDist(int x, int y, int nx, int ny) {
		int originDist = Math.abs(x - exit.x) + Math.abs(y - exit.y);
		int nextDist = Math.abs(nx - exit.x) + Math.abs(ny - exit.y);

		if (originDist > nextDist) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isBoundOrWall(int nx, int ny) {

		if (nx < 1 || nx > N || ny < 1 || ny > N || map[nx][ny] > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void rotate() {

		// 조건에 부합하는 정사각형 찾기
		int[] info = new int[3];
		info = checkRectangle();
		// 회전
		int size = info[0];
		int x = info[1];
		int y = info[2];
		Queue<Integer> q = new LinkedList<>();
		for (int i = x; i <= x + size - 1; i++) {
			for (int j = y; j <= y + size - 1; j++) {
				q.add(map[i][j]);
			}
		}

		for (int j = y + size - 1; j >= y; j--) {
			for (int i = x; i <= x + size - 1; i++) {
				map[i][j] = q.poll();
				if (map[i][j] == -9999) {
					exit.x = i;
					exit.y = j;
				} else if (map[i][j] > 0) {
					map[i][j]--;
				}
			}
		}

		check = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] < 0 && map[i][j] != -9999) {
					pArray[++check] = new Person(new Pos(i, j), map[i][j], false);
				}
			}
		}
	}

	public static int[] checkRectangle() {

		int[] retArray = new int[3];

		outerloop: for (int size = 2; size <= N; size++) {

			for (int i = 1; i <= N - size + 1; i++) {
				for (int j = 1; j <= N - size + 1; j++) {
					if (check(size, i, j)) {
						retArray[0] = size;
						retArray[1] = i;
						retArray[2] = j;
						break outerloop;
					}
				}
			}

		}

		return retArray;
	}

	public static boolean check(int size, int x, int y) {
		boolean isExit = false;
		boolean isPerson = false;
		for (int i = x; i <= x + size - 1; i++) {
			for (int j = y; j <= y + size - 1; j++) {
				if (i == exit.x && j == exit.y) {
					isExit = true;
				} else {
					for (int p = 1; p <= check; p++) {
						Person person = pArray[p];
						if (person.pos.x == i && person.pos.y == j) {
							isPerson = true;
						}
					}
				}
			}
		}

		if (isExit && isPerson) {
			return true;
		} else {
			return false;
		}
	}

	public static void printMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + "   ");
			}
			System.out.println();
		}
		System.out.println("@@@@@@@@@@@");
	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Person {
		Pos pos;
		int value;
		boolean exit;

		public Person(Pos pos, int value, boolean exit) {
			this.pos = pos;
			this.value = value;
			this.exit = exit;
		}
	}
}
