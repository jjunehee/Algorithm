import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree3 {

	static int N, M, K;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static Queue<Atom>[][] map;
	static Queue<Atom>[][] tmpMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new LinkedList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<>();
			}
		}

		// 원자 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[x][y].add(new Atom(x, y, m, s, d));
		}

		simulation();

		int result = checkResult();

		System.out.println(result);
	}

	public static int checkResult() {

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				while (!map[i][j].isEmpty()) {
					sum += map[i][j].poll().m;
				}
			}
		}

		return sum;
	}

	public static void simulation() {

		tmpMap = new LinkedList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmpMap[i][j] = new LinkedList<>();
			}
		}

		int time = 0;
		while (true) {

			if (time == K) {
				break;
			}

			// 원자 이동 드가자
			move();
			// 겹치는 원자 확인 및 조치
			checkDuplicate();
			
			initializeMap();
			time++;
		}

	}

	public static void checkDuplicate() {

		int[][] sizeInfo = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sizeInfo[i][j] = tmpMap[i][j].size();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (sizeInfo[i][j] >= 2) {

					int checkDir = 0;
					int checkMess = 0;
					int checkSpeed = 0;
					
					for (int k = 0; k < sizeInfo[i][j]; k++) {
						Atom atom = tmpMap[i][j].poll();
						checkDir += atom.d;
						checkMess += atom.m;
						checkSpeed += atom.s;
					}

					if (checkDir % 2 == 0) {

						for (int dir = 0; dir <= 6; dir += 2) {
							if (checkMess / 5 > 0) {
								tmpMap[i][j].add(new Atom(i, j, checkMess / 5, checkSpeed / sizeInfo[i][j], dir));
							}
						}

					} else {
						
						for (int dir = 1; dir <= 7; dir += 2) {
							if (checkMess / 5 > 0) {
								tmpMap[i][j].add(new Atom(i, j, checkMess / 5, checkSpeed / sizeInfo[i][j], dir));
							}
						}
						
					}

				}
			}
		}
	}

	public static void initializeMap() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				while (!tmpMap[i][j].isEmpty()) {
					map[i][j].add(tmpMap[i][j].poll());
				}
			}
		}
	}

	public static void move() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				while (!map[i][j].isEmpty()) {

					Atom now = map[i][j].poll();

					for (int s = 0; s < now.s; s++) {
						int nx = now.x + dx[now.d];
						int ny = now.y + dy[now.d];

						Point nextPoint = checkAndMove(nx, ny);

						now.x = nextPoint.x;
						now.y = nextPoint.y;
					}

					tmpMap[now.x][now.y].add(new Atom(now.x, now.y, now.m, now.s, now.d));

				}
			}
		}

	}

	public static Point checkAndMove(int nx, int ny) {
		if (nx < 0) {
			nx = N - 1;
		}

		if (nx > N - 1) {
			nx = 0;
		}

		if (ny < 0) {
			ny = N - 1;
		}

		if (ny > N - 1) {
			ny = 0;
		}

		return new Point(nx, ny);
	}

	public static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Atom {
		int x, y;
		int m, s, d;

		public Atom(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
