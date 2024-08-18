import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SS_2023_1 {

	static int N, M, K;
	static Pos[][] map;
	static Pos attacker, defender;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Pos[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int power = Integer.parseInt(st.nextToken());
				map[i][j] = new Pos(i, j, power, 0);
			}
		}

		// 공격자 선정
		attacker = findAttacker();

		// 방어자 선정
		defender = findDefender();

		// 레이저 공격 가능여부 체크
		boolean canLaser = checkLaser(attacker.x, attacker.y);

		if (canLaser) { // 레이저 공격 가능할시 레이저 공격
//			LaserAttack();
		} else { // 불가능하다면 포탄 공격
//			bombAttack();
		}

		System.out.println(attacker.x + " " + attacker.y);
		System.out.println(defender.x + " " + defender.y);
	}

	// 우선순위에 맞게 공격자를 선정한다.
	public static Pos findAttacker() {

		PriorityQueue<Pos> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j].power > 0) {
					pq.add(map[i][j]);
				}
			}
		}

		return pq.poll();
	}

	public static Pos findDefender() {

		PriorityQueue<Pos> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j].power > 0) {
					pq.add(map[i][j]);
				}
			}
		}

		int size = pq.size();
		for (int i = 0; i < size - 1; i++) {
			pq.poll();
		}

		return pq.poll();
	}

	public static boolean checkLaser(int x, int y) {

		if (x == defender.x && y == defender.y) {
			return true;
		}
		
		// check by dfs
		int nx, ny;
		for (int dir = 0; dir < 4; dir++) {
			nx = x + dx[dir];
			ny = y + dy[dir];
			if (isBound(nx, ny)) {
				nx = change("x", nx);
				ny = change("y", ny);
			}
			checkLaser(nx, ny);
		}

		return false;
	}

	public static boolean isBound(int nx, int ny) {
		if (nx < 0 || ny >= N || ny < 0 || ny >= M) {
			return true;
		}
		return false;
	}

	public static int change(String type, int value) {
		switch (type) {
		case "x":
			if (value >= N) {
				return 0;
			} else if (value < 0) {
				return N;
			} else {
				return value;
			}
		case "y":
			if (value >= M) {
				return 0;
			} else if (value < 0) {
				return M;
			} else {
				return value;
			}
		}
		return -1;
	}

	public static class Pos implements Comparable<Pos> {
		int x;
		int y;
		int power;
		int attackTime;

		public Pos(int x, int y, int power, int attackTime) {
			this.x = x;
			this.y = y;
			this.power = power;
			this.attackTime = attackTime;
		}

		public int compareTo(Pos o) {
			if (this.power == o.power) {
				if (this.attackTime == o.attackTime) {
					if ((this.x + this.y) == (o.x + o.y)) {
						return o.y - this.y;
					}
					return (o.x + o.y) - (this.x + this.y);
				}
				return o.attackTime - this.attackTime;
			}
			return this.power - o.power;
		}

	}
}
