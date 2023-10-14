import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree6 {

	static int N, M, K;
	static int[][] map;
	static boolean[][] check;
	static PriorityQueue<Pos> pq;
	static int time;
	static int[][] attackTime;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int[] ddx = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[] ddy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		check = new boolean[N][M];
		attackTime = new int[N][M];
		// 맵 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = simulation();
		System.out.println(result);
	}

	public static int simulation() {

		time = 1;

		while (time <= K) {

			initialCheck();

			if (checkAlive()) {
				return getResult();
			}

			pq = new PriorityQueue<>();
			// 공격자 선정
			Pos attacker = searchAttacker();
			// 타겟 선정
			Pos target = searchTarget();

			attacker.power += (N + M);
			map[attacker.x][attacker.y] += (N + M);

			// 공격
			if (canLaser(attacker, target)) {
				attackLaser(attacker, target);
			} else { // 포탄 공격
				attackRocket(attacker, target);
			}
			
			// 정비
			heal();

			time++;
		}

		return getResult();

	}

	public static void heal() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0 && !check[i][j]) {
					map[i][j]++;
				}
			}
		}
	}

	public static void initialCheck() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				check[i][j] = false;
			}
		}
	}

	public static void attackRocket(Pos attacker, Pos target) {

		// target 데미지
		map[target.x][target.y] -= attacker.power;
		if(map[target.x][target.y] < 0) {
			map[target.x][target.y] = 0;
		}
		attackTime[attacker.x][attacker.y] = time;
		check[attacker.x][attacker.y] = true;
		check[target.x][target.y] = true;

		int nx, ny;
		for (int dir = 0; dir < 8; dir++) {
			nx = target.x + ddx[dir];
			ny = target.y + ddy[dir];

			Pos newPos = checkBound(nx, ny);

			if (newPos.x == attacker.x && newPos.y == attacker.y) {
				continue;
			}
			if(map[newPos.x][newPos.y] == 0) {
				continue;
			}

			// 간접 데미지
			map[newPos.x][newPos.y] -= (attacker.power / 2);
			if(map[newPos.x][newPos.y] < 0) {
				map[newPos.x][newPos.y] = 0;
			}
			check[newPos.x][newPos.y] = true;
		}
	}
	
	public static boolean canLaser(Pos attacker, Pos target) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(attacker.x, attacker.y));

		boolean[][] visited = new boolean[N][M];

		while (!q.isEmpty()) {
			Pos now = q.poll();

			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];

				Pos newPos = checkBound(nx, ny);

				if (map[newPos.x][newPos.y] == 0 || visited[newPos.x][newPos.y]) {
					continue;
				}

				if (newPos.x == target.x && newPos.y == target.y) {
					return true;
				}

				visited[newPos.x][newPos.y] = true;
				q.add(new Pos(newPos.x, newPos.y));

			}
		}
		
		return false;

	}

	public static boolean attackLaser(Pos attacker, Pos target) {

		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(attacker.x, attacker.y));

		Pos[][] routeInfo = new Pos[N][M];
		boolean[][] visited = new boolean[N][M];
		visited[attacker.x][attacker.y] = true;

		outerloop: while (!q.isEmpty()) {
			Pos now = q.poll();

			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];

				Pos newPos = checkBound(nx, ny);

				if (map[newPos.x][newPos.y] == 0 || visited[newPos.x][newPos.y]) {
					continue;
				}

				if (newPos.x == target.x && newPos.y == target.y) {
					routeInfo[newPos.x][newPos.y] = new Pos(now.x, now.y);
					break outerloop;
				}

				routeInfo[newPos.x][newPos.y] = new Pos(now.x, now.y);
				visited[newPos.x][newPos.y] = true;
				q.add(new Pos(newPos.x, newPos.y));

			}
		}

		if (routeInfo[target.x][target.y] != null) {

			// target 데미지
			map[target.x][target.y] -= attacker.power;
			if(map[target.x][target.y] < 0) {
				map[target.x][target.y] = 0;
			}
			attackTime[attacker.x][attacker.y] = time;
			check[attacker.x][attacker.y] = true;
			check[target.x][target.y] = true;

			int tmpX = target.x;
			int tmpY = target.y;

			// 간접 데미지
			while (true) {
				Pos target2 = routeInfo[tmpX][tmpY];

					
				if (target2.x == attacker.x && target2.y == attacker.y) {
					break;
				}

				map[target2.x][target2.y] -= (attacker.power / 2);
				if(map[target2.x][target2.y] < 0) {
					map[target2.x][target2.y] = 0;
				}
				check[target2.x][target2.y] = true;

				tmpX = target2.x;
				tmpY = target2.y;
			}

			return true;

		} else {
			return false;
		}

	}

	public static Pos checkBound(int nx, int ny) {

		if (nx < 0) {
			nx = N - 1;
		}

		if (nx >= N) {
			nx = 0;
		}

		if (ny < 0) {
			ny = M - 1;
		}

		if (ny >= M) {
			ny = 0;
		}

		return new Pos(nx, ny);
	}

	public static Pos searchAttacker() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					pq.add(new Pos(i, j, map[i][j]));
				}
			}
		}

		return pq.poll();
	}

	public static Pos searchTarget() {
		Pos target = null;
		while (!pq.isEmpty()) {
			target = pq.poll();
		}

		return target;
	}

	public static boolean checkAlive() {

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					cnt++;
				}
			}
		}

		if (cnt == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static int getResult() {
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					max = Math.max(max, map[i][j]);
				}
			}
		}

		return max;
	}

	public static class Pos implements Comparable<Pos> {
		int x, y;
		int power;

		public Pos(int x, int y, int power) {
			this.x = x;
			this.y = y;
			this.power = power;
		}

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.power == o.power) {
				if (attackTime[this.x][this.y] == attackTime[o.x][o.y]) {
					if ((this.x + this.y) == (o.x + o.y)) {
						return o.y - this.y;
					} else {
						return (o.x + o.y) - (this.x + this.y);
					}
				} else {
					return attackTime[o.x][o.y] - attackTime[this.x][this.y];
				}
			} else {
				return this.power - o.power;
			}
		}
	}

	public static void pritnMap() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
