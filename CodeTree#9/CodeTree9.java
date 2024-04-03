
import java.util.*;
import java.io.*;

public class CodeTree9 {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Person {
		Pos pos;
		int dir;
		int type;
		boolean die;

		public Person(Pos pos, int dir, int type, boolean die) {
			this.pos = pos;
			this.dir = dir;
			this.type = type;
			this.die = die;
		}
	}

	static int N, M, H, K;
	static int[][] map;
	static Person[] pArray;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Pos hunter;
	static boolean[][] isTree;
	static int hunterDir = 0;
	static int score;
	static int turn;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// map 크키
		N = Integer.parseInt(st.nextToken());
		// 도망자 수
		M = Integer.parseInt(st.nextToken());
		// 나무의 수
		H = Integer.parseInt(st.nextToken());
		// 시간
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];

		pArray = new Person[M + 1];
		// 도망자 정보
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int type = Integer.parseInt(st.nextToken());
			if (type == 1) {
				pArray[i] = new Person(new Pos(x, y), 1, 1, false);
			} else if (type == 2) {
				pArray[i] = new Person(new Pos(x, y), 2, 2, false);
			}
		}

		isTree = new boolean[N + 1][N + 1];
		// 나무 정보
		for (int i = 1; i <= H; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			isTree[x][y] = true;
		}

		// 술래 정보
		map[N / 2 + 1][N / 2 + 1] = -1;
		hunter = new Pos(N / 2 + 1, N / 2 + 1);
		simulation();
		System.out.println(score);
	}

	public static void simulation() {

		int time = 0;

		int check = 1;
		int cnt = 0;
		int changeDir = 0;

		boolean reverse = false;
		turn = 1;
		while (time < K) {

			// 도망자 움직임
			escapeMove();

			// 술래 움직임
			hunterMove(hunterDir);
			cnt++;
			if (cnt == check) {
				changeDir++;

				// 거리가 변경
				if (changeDir == 2) {

					changeDir = 0;
					if (reverse) {
						check--;
					} else {
						check++;
					}
				}

				// 방향이 바뀜
				if (reverse) {
					hunterDir = (hunterDir + 3) % 4;
				} else {
					hunterDir = (hunterDir + 1) % 4;
				}
				cnt = 0;
			}
			if (hunter.x == 1 && hunter.y == 1) {
				reverse = true;
				changeDir = -1;
				hunterDir = 2;
				check--;
				cnt = 0;
			} else if (hunter.x == N / 2 + 1 && hunter.y == N / 2 + 1) {
				reverse = false;
				hunterDir = 0;
				cnt = 0;
				check++;
			}
//			printMap();
//			printEMap();
			// 사냥
			hunt();
			turn++;
//			System.out.println("시간" + time);
			time++;
		}
	}

	public static void escapeMove() {

		for (int i = 1; i <= M; i++) {

			Person person = pArray[i];

			if (!checkDist(person.pos.x, person.pos.y)) {
				continue;
			}

			if (person.die) {
				continue;
			}

			int nx = person.pos.x + dx[person.dir];
			int ny = person.pos.y + dy[person.dir];

			if (isBound(nx, ny)) {
				person.dir = (person.dir + 2) % 4;
				nx = person.pos.x + dx[person.dir];
				ny = person.pos.y + dy[person.dir];
			}

			if (isHunter(nx, ny)) {
				return;
			} else {
				// 이동 완료
				person.pos.x = nx;
				person.pos.y = ny;
			}

		}
	}

	public static boolean checkDist(int x, int y) {
		int dist = Math.abs(hunter.x - x) + Math.abs(hunter.y - y);

		if (dist <= 3) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isBound(int nx, int ny) {

		if (nx < 1 || nx > N || ny < 1 || ny > N) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isHunter(int nx, int ny) {

		if (map[nx][ny] == -1) {
			return true;
		} else {
			return false;
		}
	}

	public static void hunterMove(int hunterDir) {
		map[hunter.x][hunter.y] = 0;
		hunter.x = hunter.x + dx[hunterDir];
		hunter.y = hunter.y + dy[hunterDir];
		map[hunter.x][hunter.y] = -1;
	}

	public static void hunt() {

		int huntCnt = 0;

		if (!isTree[hunter.x][hunter.y]) {
			for (int i = 1; i <= M; i++) {

				Person person = pArray[i];
				if (person.die) {
					continue;
				}

				if (person.pos.x == hunter.x && person.pos.y == hunter.y) {
					person.die = true;
					huntCnt++;
				}
			}
		}
		int nx = hunter.x;
		int ny = hunter.y;

		for (int dist = 1; dist <= 2; dist++) {
			nx = nx + dx[hunterDir];
			ny = ny + dy[hunterDir];
			if (isBound(nx, ny)) {
				break;
			}

			if (!isTree[nx][ny]) {
				for (int i = 1; i <= M; i++) {

					Person person = pArray[i];
					if (person.die) {
						continue;
					}

					if (person.pos.x == nx && person.pos.y == ny) {
						person.die = true;
						huntCnt++;
					}
				}
			}

		}

		score += (turn * huntCnt);
	}

	public static void printMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + "   ");
			}
			System.out.println();
		}
		System.out.println(hunterDir);

	}

}
