import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree10 {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Monster {
		Pos pos;
		int dir;
		boolean dead;

		public Monster(Pos pos, int dir, boolean dead) {
			this.pos = pos;
			this.dir = dir;
			this.dead = dead;
		}
	}

	static int[][] map;
	static int m, t;
	static Pos packMan;
	static List<Monster> mList;
	static Queue<Monster> q;
	static int[] dfsRet;
	static int max;
	static Pos[] moveHistory;
	static boolean[][] visited;

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] pacdx = { -1, 0, 1, 0 };
	static int[] pacdy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		map = new int[5][5];

		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		packMan = new Pos(x, y);

		mList = new ArrayList<>();
		// 몬스터 입력
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			mList.add(new Monster(new Pos(x, y), dir - 1, false));
		}

		int ret = lego();
		System.out.println(ret);

	}

	static int lego() {

		int turn = 0;

		while (turn < t) {

			// 복제 시도
			copyMonster();
			// 몬스터 이동
			moveMonster();
			// 팩맨 이동
			movePac();
			// 시체 소멸
			deleteDead();
			// 복제 완료
			copyComplete();
			turn++;
		}

		return calculateAlive();
	}

	static void copyMonster() {
		q = new LinkedList<>();
		

		for (int i = 0; i < mList.size(); i++) {
			Monster monster = mList.get(i);

			// 죽은 몬스터는 복제 할 필요 없음.
			if (monster.dead) {
				continue;
			}
			// 살아있는 몬스터들 복제한다.
			q.add(new Monster(new Pos(monster.pos.x, monster.pos.y), monster.dir, false));
		}
	}

	static void moveMonster() {

		for (int i = 0; i < mList.size(); i++) {
			Monster monster = mList.get(i);

			if (monster.dead) {
				continue;
			}
			int nx, ny;

			for (int dir = 0; dir < 9; dir++) {

				nx = monster.pos.x + dx[monster.dir];
				ny = monster.pos.y + dy[monster.dir];

				if (isBoundOrDeadOrPac(nx, ny)) {
					monster.dir = (monster.dir + 1) % 8;
					continue;
				}

				// 이동 가능하다면, 이동?
				monster.pos.x = nx;
				monster.pos.y = ny;
				break;
			}

		}
	}

	static void movePac() {

		// dfs로 64개 모두 이동해볼거야.
		dfsRet = new int[3];
		max = Integer.MIN_VALUE;
		Pos[] history = new Pos[3];
		moveHistory = new Pos[3];
		visited = new boolean[5][5];
		visited[packMan.x][packMan.y] = true;
		search(packMan.x, packMan.y, 0, 0, history);
		killMonster();
		packMan.x = dfsRet[0];
		packMan.y = dfsRet[1];
	}

	static void search(int x, int y, int depth, int value, Pos[] history) {

		if (depth == 3) {

			if (value > max) {
				max = value;
				dfsRet[0] = x;
				dfsRet[1] = y;
				dfsRet[2] = value;
				for (int i = 0; i < 3; i++) {
					moveHistory[i] = history[i];
				}
			}
			return;
		}

		int nx, ny;
		for (int dir = 0; dir < 4; dir++) {
			nx = x + pacdx[dir];
			ny = y + pacdy[dir];
			if (isBound(nx, ny)) {
				continue;
			}
			history[depth] = new Pos(nx, ny);

			int sum = 0;
			for (int i = 0; i < mList.size(); i++) {
				Monster monster = mList.get(i);
				if (monster.dead) {
					continue;
				}

				if (monster.pos.x == nx && monster.pos.y == ny && !visited[nx][ny]) {
					sum++;
				}
			}
			visited[nx][ny] = true;
			search(nx, ny, depth + 1, value + sum, history);
			visited[nx][ny] = false;
		}
	}

	static void killMonster() {
		for (Pos pos : moveHistory) {

			for (int i = 0; i < mList.size(); i++) {
				Monster monster = mList.get(i);
				if (monster.dead) {
					continue;
				}

				if (monster.pos.x == pos.x && monster.pos.y == pos.y) {
					monster.dead = true;
					map[pos.x][pos.y] = -3;
				}
			}
		}
	}

	static void deleteDead() {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (map[i][j] < 0) {
					map[i][j]++;
				}
			}
		}
	}

	static void copyComplete() {

		while (!q.isEmpty()) {
			Monster monster = q.poll();
			mList.add(new Monster(new Pos(monster.pos.x, monster.pos.y), monster.dir, false));
		}
	}

	static int calculateAlive() {
		int sum = 0;
		for (int i = 0; i < mList.size(); i++) {
			Monster monster = mList.get(i);
			if (monster.dead) {
				continue;
			} else {
				sum++;
			}
		}
		return sum;
	}

	static boolean isBound(int nx, int ny) {

		if (nx < 1 || nx > 4 || ny < 1 || ny > 4) {
			return true;
		} else {
			return false;
		}
	}

	static boolean isBoundOrDeadOrPac(int nx, int ny) {

		if (nx < 1 || nx > 4 || ny < 1 || ny > 4 || map[nx][ny] < 0 || (packMan.x == nx && packMan.y == ny)) {
			return true;
		} else {
			return false;
		}
	}

	static void printMonster() {
		for (int i = 0; i < mList.size(); i++) {
			Monster monster = mList.get(i);
			System.out.println(monster.pos.x + " " + monster.pos.y + " " +monster.dead);
		}
	}

	static void printMap() {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("@@@@@@@@");
	}
}
