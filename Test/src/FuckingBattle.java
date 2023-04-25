import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FuckingBattle {

	static PriorityQueue<Integer>[][] pq;
	static int N, M, K;
	static ArrayList<Player> playerList = new ArrayList<>();
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] scoreBoard;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		pq = new PriorityQueue[N + 1][N + 1];
		scoreBoard = new int[M + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				pq[i][j] = new PriorityQueue<>(Collections.reverseOrder());
			}
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				pq[i][j].add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			playerList.add(new Player(i, x, y, d, s, 0));
		}

		gameStart();

		for (int i = 1; i <= M; i++) {
			System.out.print(scoreBoard[i] + " ");
		}

	}

	private static void gameStart() {

		int round = 1;

		// 게임 시작
		while (round <= K) {

			playerMove();

			round++;
		}

	}

	private static void playerMove() {

		for (int i = 0; i < playerList.size(); i++) {

			Player player = playerList.get(i);

			int nx = player.x + dx[player.dir];
			int ny = player.y + dy[player.dir];

			if (!boundCheck(nx, ny)) {
				player.dir = (player.dir + 2) % 4;
				nx = player.x + dx[player.dir];
				ny = player.y + dy[player.dir];
			}

			// 이동할 위치에 플레이어가 있는 지 확인
			Player player2 = checkArea(nx, ny);

			// 일단 이동
			player.x = nx;
			player.y = ny;

			if (player2 != null) { // 사람이 있는 경우
				fight(player, player2);
			} else { // 사람이 없는 경우
				getGun(player);
			}
		}
	}

	private static void fight(Player p1, Player p2) {
		int totalP1 = p1.ability + p1.gun;
		int totalP2 = p2.ability + p2.gun;

		if (totalP1 > totalP2) { // p1 승리

			loseMove(p2);
			getGun(p1);
			scoreBoard[p1.num] += (totalP1 - totalP2);

		} else if (totalP1 < totalP2) { // p2 승리

			loseMove(p1);
			getGun(p2);
			scoreBoard[p2.num] += (totalP2 - totalP1);
		} else if (totalP1 == totalP2) {

			if (p1.ability > p2.ability) { // p1 승리

				loseMove(p2);
				getGun(p1);

			} else { // p2 승리
				loseMove(p1);
				getGun(p2);
			}
		}
	}

	private static void loseMove(Player player) {
		// 총 버려 이자식아
		if (player.gun > 0) { // 플레이가 총이 있었다면
			pq[player.x][player.y].add(player.gun);
			player.gun = 0;
		}

		int nx = player.x + dx[player.dir];
		int ny = player.y + dy[player.dir];

		while (!loserMoveCheck(nx, ny)) {
			player.dir = (player.dir + 1) % 4;
			nx = player.x + dx[player.dir];
			ny = player.y + dy[player.dir];
		}
		player.x = nx;
		player.y = ny;
		getGun(player);
	}

	private static boolean loserMoveCheck(int nx, int ny) {
		if (nx >= 1 && nx <= N && ny >= 1 && ny <= N && checkArea(nx, ny) == null) {
			return true;
		} else {
			return false;
		}
	}

	private static void getGun(Player player) {

		if (!pq[player.x][player.y].isEmpty()) {
			if (player.gun < pq[player.x][player.y].peek()) {
				pq[player.x][player.y].add(player.gun);
				player.gun = pq[player.x][player.y].poll();
			}
		}
	}

	private static Player checkArea(int x, int y) {

		for (int i = 0; i < playerList.size(); i++) {
			Player player = playerList.get(i);

			if (player.x == x && player.y == y) {
				return player;
			}
		}
		return null;

	}

	public static class Player {
		int num;
		int x, y;
		int dir;
		int ability;
		int gun;

		public Player(int num, int x, int y, int dir, int ability, int gun) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.ability = ability;
			this.gun = gun;
		}

	}

	private static boolean boundCheck(int nx, int ny) {
		if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
			return true;
		} else {
			return false;
		}
	}
}
