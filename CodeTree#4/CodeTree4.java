
// 나무 박멸

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CodeTree4 {

	static int n, m, k, c;

	static int[][] map;
	static int[][] plusMap;
	static boolean[][] isMedicine;
	static int[][] expireTime;
	static int time;
	static int result;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static int[] dA = { 1, 1, -1, -1 };
	static int[] dB = { 1, -1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		// 맵 입력
		map = new int[n][n];
		plusMap = new int[n][n];
		isMedicine = new boolean[n][n];
		expireTime = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 시뮬레이션 시작
		simulation();
		System.out.println(result);

	}

	public static void simulation() {

		while (true) {

			if (time == m) {
				break;
			}

			// 제초제 제거
			remove();

			// 성장
			grow();
			tmpToMap();

			// 번식
			spread();
			tmpToMap();

			// 제초제 선택, 처리
			kill();

			time++;
		}
	}

	public static void remove() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (expireTime[i][j] > 0) {
					if (expireTime[i][j] == time) {
						isMedicine[i][j] = false;
					}
				}
			}
		}
	}

	public static void tmpToMap() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (plusMap[i][j] > 0) {
					map[i][j] += plusMap[i][j];
					plusMap[i][j] = 0;
				}
			}
		}
	}

	public static void grow() {

		// 나무 성장
		// 주변에 나무 수 만큼 성장
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (map[i][j] > 0 && !isMedicine[i][j]) { // 제초제가 없어서 나무가 있는 곳.
					int cnt = 0;
					int nx, ny;
					for (int dir = 0; dir < 4; dir++) {
						nx = i + dx[dir];
						ny = j + dy[dir];

						if (checkBound(nx, ny)) { // 경계를 넘음
							continue;
						}

						if (map[nx][ny] > 0) { // 나무가 있다면
							cnt++;
						}
					}
					plusMap[i][j] += cnt;
				}
			}
		}
	}

	public static void spread() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0) { // 이 나무에 대해서

					int nx, ny;
					int cnt = 0;
					for (int dir = 0; dir < 4; dir++) {
						nx = i + dx[dir];
						ny = j + dy[dir];

						if (checkBound(nx, ny)) {
							continue;
						}

						// 벽, 다른 나무, 제초제가 없어야한다.
						if (map[nx][ny] != -1 && map[nx][ny] == 0 && !isMedicine[nx][ny]) {
							cnt++;
						}
					}

					if (cnt > 0) {
						int spread = map[i][j] / cnt;
						for (int dir = 0; dir < 4; dir++) {
							nx = i + dx[dir];
							ny = j + dy[dir];

							if (checkBound(nx, ny)) {
								continue;
							}

							// 벽, 다른 나무, 제초제가 없어야한다.
							if (map[nx][ny] != -1 && map[nx][ny] == 0 && !isMedicine[nx][ny]) {
								plusMap[nx][ny] += spread;
							}
						}
					}

				}
			}
		}
	}

	public static void kill() {

		PriorityQueue<Tree> pq = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0) { // 나무가 있다면

					int cntKill = 0;
					// 제초제 뿌리는 시뮬레이션
					cntKill += map[i][j];
					// 대각선 방향
					for (int dir = 0; dir < 4; dir++) {

						int nx = i;
						int ny = j;
						outerloop: while (true) {
							for (int dist = 0; dist < k; dist++) {

								nx = nx + dA[dir];
								ny = ny + dB[dir];

								if (checkBound(nx, ny)) { // 경계를 넘어감.
									break outerloop;
								}

								if (map[nx][ny] == 0 || map[nx][ny] == -1) { // 빈칸이거나, 벽이 있을 때
									break outerloop;
								}
								cntKill += map[nx][ny];
							}

						}

					}
					pq.add(new Tree(i, j, cntKill));
				}
			}
		}

		if (pq.size() > 0) {
			Tree tree = pq.poll();

			// 제초제 뿌리는 시뮬레이션
			// 대각선 방향
			isMedicine[tree.x][tree.y] = true;
			expireTime[tree.x][tree.y] = time + c + 1;
			map[tree.x][tree.y] = 0;
			for (int dir = 0; dir < 4; dir++) {

				int nx = tree.x;
				int ny = tree.y;
				outerloop2: while (true) {

					for (int dist = 0; dist < k; dist++) {
						nx = nx + dA[dir];
						ny = ny + dB[dir];

						if (checkBound(nx, ny)) { // 경계를 넘어감.
							break outerloop2;
						}

						if (map[nx][ny] == 0 || map[nx][ny] == -1) { // 빈칸이거나, 벽이 있을 때
							isMedicine[nx][ny] = true;
							expireTime[nx][ny] = time + c + 1;
							break outerloop2;
						}

						isMedicine[nx][ny] = true;
						expireTime[nx][ny] = time + c + 1;
						map[nx][ny] = 0;

					}
				}
			}

			result += tree.kill;
		}

	}

	public static boolean checkBound(int nx, int ny) {

		if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
			return true;
		}

		return false;
	}

	public static class Tree implements Comparable<Tree> {
		int x, y;
		int kill;

		public Tree(int x, int y, int kill) {
			this.x = x;
			this.y = y;
			this.kill = kill;
		}

		@Override
		public int compareTo(Tree o) {
			if (this.kill == o.kill) {
				if (this.x == o.x) {
					return this.y - o.y;
				} else {
					return this.x - o.x;
				}
			} else {
				return o.kill - this.kill;
			}
		}
	}

	public static void printMap() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
