
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class solution1 {

	static int L, N, Q;
	static int[][] map;
	static boolean[][] isTrap;
	static boolean[][] isWall;
	static pushMan[] manArray;
	static boolean[] pushCheck;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		map = new int[L + 1][L + 1];
		isTrap = new boolean[L + 1][L + 1];
		isWall = new boolean[L + 1][L + 1];

		for (int i = 1; i <= L; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= L; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					isTrap[i][j] = true;
					map[i][j] = 0;
				} else if (map[i][j] == 2) {
					isWall[i][j] = true;
					map[i][j] = 0;
				}
			}
		}

		manArray = new pushMan[N + 1];
		// 기사 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			manArray[i] = new pushMan(r, c, h, w, k, 0);
		}

		drawMap();

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			if (manArray[idx].k <= 0) {
				continue;
			}

			pushCheck = new boolean[N + 1];
			
			if (check(idx, direction)) {
				push(idx, direction);
				damage(idx);
			}
			
		}
		

		System.out.println(calculate());

	}

	public static int calculate() {
		int ret = 0;
		for (int i = 1; i <= N; i++) {
			if(manArray[i].k > 0) {
				ret += manArray[i].damage;
			}
		}
		
		return ret;
	}

	public static void damage(int idx) {

		for (int i = 1; i <= N; i++) {

			if (idx == i || !pushCheck[i]) {
				continue;
			}

			pushMan man = manArray[i];

			if (man.k <= 0) {
				continue;
			}

			for (int r = man.r; r < man.r + man.h; r++) {
				for (int c = man.c; c < man.c + man.w; c++) {
					if (isTrap[r][c]) {
						man.damage++;
						man.k--;
					}
				}
			}

			if (man.k <= 0) {
				drawMap();
			}

		}

	}

	public static boolean check(int idx, int direction) {

		pushMan man = manArray[idx];

		pushCheck[idx] = true;

		for (int r = man.r; r < man.r + man.h; r++) {
			for (int c = man.c; c < man.c + man.w; c++) {

				int nx, ny;

				while (true) {
					nx = r + dx[direction];
					ny = c + dy[direction];

					if (isWall(nx, ny)) {
						return false;
					}

					// 일부분인 곳이라면 가능
					if (map[nx][ny] == idx) {
						break;
					}

					// 가장 끝에 있는 놈이 그 방향을 봤는데 빈칸이다? 그럼 성공
					if (map[nx][ny] == 0) {
						break;
					}

					// 다른 놈이라면 그놈도 체크 해야함
					if (map[nx][ny] != idx) {
						// 그놈을 체크했는데 false 나오면 바로 false;
						if (!check(map[nx][ny], direction)) {
							return false;
						} else {
							break;
						}
					}

				}

			}
		}

		return true;

	}

	public static boolean isWall(int nx, int ny) {
		if (nx < 1 || nx > L || ny < 1 || ny > L || isWall[nx][ny]) {
			return true;
		} else {
			return false;
		}
	}

	public static void push(int idx, int direction) {

		for (int i = 1; i <= N; i++) {
			if (pushCheck[i]) {
				manArray[i].r += dx[direction];
				manArray[i].c += dy[direction];
			}
		}

		drawMap();

	}

	public static void drawMap() {

		// map 초기
		for (int i = 1; i <= L; i++) {
			for (int j = 1; j <= L; j++) {
				map[i][j] = 0;
			}
		}

		for (int i = 1; i <= N; i++) {

			pushMan man = manArray[i];
			if (man.k <= 0) {
				continue;
			}

			for (int r = man.r; r < man.r + man.h; r++) {
				for (int c = man.c; c < man.c + man.w; c++) {
					map[r][c] = i;
				}
			}
		}
	}

	public static void printMap() {

		for (int i = 1; i <= L; i++) {
			for (int j = 1; j <= L; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("@@@@@@@@@@@");
	}

	public static class pushMan {
		int r, c, h, w, k;
		int damage;

		public pushMan(int r, int c, int h, int w, int k, int damage) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
			this.damage = damage;
		}
	}

}
