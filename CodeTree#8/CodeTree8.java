import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CodeTree8 {

	static int N, M, K;

	static int[][] map;
	static int[][] wall;
	static boolean[][] isWall;
	static Person[] pArray;
	static Pos exit;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		wall = new int[N + 1][N + 1];
		isWall = new boolean[N + 1][N + 1];
		// map 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					wall[i][j] = map[i][j];
					map[i][j] = 0;
					isWall[i][j] = true;
				}
			}
		}

		int x, y;
		pArray = new Person[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			pArray[i] = new Person(new Pos(x, y), 0, false);
			map[x][y] = 1;
		}

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		exit = new Pos(x, y);

		printMap();
		lego();
	}

	public static void lego() {
		int time = 0;
		while (time < K) {

			// 움직인다.
			move();
			printMap();
			// 회전한다.
			rotate();
			time++;
		}

	}

	public static void move() {

		// 한 사람씩 움직일 수 있는지 확인 후 움직이기!
		for (int i = 1; i <= M; i++) {

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
					pArray[i].pos.x = nx;
					pArray[i].pos.y = ny;
					pArray[i].move++;
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
				map[i][j] = 0;
			}
		}

		for (int i = 1; i <= M; i++) {

			Person person = pArray[i];
			if(person.exit) {
				continue;
			}
			map[person.pos.x][person.pos.y] = 1;
			
		}
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

		if (nx < 1 || nx > N || ny < 1 || ny > N || isWall[nx][ny]) {
			return true;
		} else {
			return false;
		}
	}

	public static void rotate() {
		
		// 조건에 부합하는 정사각형 찾기
		int[] info = {};
		info = checkRectangle();
		// 회전
		
		
	}
	
	public static int[] checkRectangle() {
		
		
		return 
	}

	public static void printMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + " ");
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
		int move;
		boolean exit;

		public Person(Pos pos, int move, boolean exit) {
			this.pos = pos;
			this.move = move;
			this.exit = exit;
		}
	}
}
