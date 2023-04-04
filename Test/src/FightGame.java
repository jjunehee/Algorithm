import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FightGame {

	static int N, M, K;
	static ArrayList<Player> pList;
	static int[] scoreBoard;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int round;
	static PriorityQueue<Integer>[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new PriorityQueue[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = new PriorityQueue<>(Collections.reverseOrder());
			}
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j].add(Integer.parseInt(st.nextToken()));
			}
		}
		pList = new ArrayList<>();
		pList.add(new Player(-1, -1, -1, -1, 0));
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			pList.add(new Player(x, y, d, s, 0));
		}

		scoreBoard = new int[M + 1];
		GameStart();
		for (int i = 1; i <= M; i++) {
			System.out.print(scoreBoard[i] + " ");
		}
	}

	private static void GameStart() {

		round = 1;
		while (round <= K) {
			for (int i = 1; i <= M; i++) {
				Player player = pList.get(i); // 해당 선수 차례시작.
				int dir = player.dir; // 이 선수가 갈 방향.

				// 다음에 갈 좌표
				int nx = player.x + dx[dir];
				int ny = player.y + dy[dir];

				if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) { // 경계를 안넘었다.

					int num = checkPlayer(nx, ny);
					if (num != -1) { // 플레이어가 있는 경우
						pList.get(i).x = nx;
						pList.get(i).y = ny;
						fight(num, i); // 그 자리에 있던 num과 i가 싸움.
					} else { // 빈칸 or 총이 있는 경우
						pList.get(i).x = nx;
						pList.get(i).y = ny;
						if (!map[nx][ny].isEmpty()) {
							if (pList.get(i).gun < map[nx][ny].peek()) {
								int tmp = pList.get(i).gun;
								pList.get(i).gun = map[nx][ny].poll();
								map[nx][ny].add(tmp);
							}
						}
					}

				} else { // 경계를 넘었으면 반대 방향으로 가기
					pList.get(i).dir = ((pList.get(i).dir + 2) % 4); // 반대로 회전
					// 다음에 갈 좌표
					nx = player.x + dx[pList.get(i).dir];
					ny = player.y + dy[pList.get(i).dir];

					if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) { // 경계를 안넘었다.

						int num = checkPlayer(nx, ny);
						if (num != -1) { // 플레이어가 있는 경우

							pList.get(i).x = nx;
							pList.get(i).y = ny;
							fight(num, i); // 그 자리에 있던 num과 i가 싸움.

						} else { // 빈칸 or 총이 있는 경우
							pList.get(i).x = nx;
							pList.get(i).y = ny;

							if (!map[nx][ny].isEmpty()) {
								if (pList.get(i).gun < map[nx][ny].peek()) {
									int tmp = pList.get(i).gun;
									pList.get(i).gun = map[nx][ny].poll();
									map[nx][ny].add(tmp);
								}
							}
						}

					}
				}
			}
			round++;
		}
	}

	private static void fight(int p1, int p2) {
		Player player1 = pList.get(p1);
		Player player2 = pList.get(p2);

		int p1Total = player1.ability + player1.gun;
		int p2Total = player2.ability + player2.gun;
		if (p1Total > p2Total) { // p1 승리
			// 진 사람은 총 버린 후 이동.
			// 두개가 버려진다면 작은 것은 굳이 유지하고 있을 필요가 없음.
			// No. 유지 해야한다.....
			if (player2.gun != 0) {
				map[player2.x][player2.y].add(player2.gun);
			}
			pList.get(p2).gun = 0;
			loserMove(p2);

			// 이긴 사람은 총이 본인 것보다 더 쎄다면 줍고, 본인이 가지고 있던 총 내려놓기.
			if (!map[player1.x][player1.y].isEmpty()) {
				if (player1.gun < map[player1.x][player1.y].peek()) {
					int tmp = pList.get(p1).gun;
					pList.get(p1).gun = map[player1.x][player1.y].poll();
					map[player1.x][player1.y].add(tmp);
				}
			}
			scoreBoard[p1] += (p1Total - p2Total);

		} else if (p1Total < p2Total) { // p2 승리
			// 진 사람은 총 버린 후 이동.
			// 두개가 버려진다면 작은 것은 굳이 유지하고 있을 필요가 없음.
			if (player1.gun != 0) {
				map[player1.x][player1.y].add(player1.gun);
			}
			pList.get(p1).gun = 0;
			loserMove(p1);

			// 이긴 사람은 총이 본인 것보다 더 쎄다면 줍고, 본인이 가지고 있던 총 내려놓기.
			if (!map[player2.x][player2.y].isEmpty()) {
				if (player2.gun < map[player2.x][player2.y].peek()) {
					int tmp = pList.get(p2).gun;
					pList.get(p2).gun = map[player2.x][player2.y].poll();
					map[player2.x][player2.y].add(tmp);
				}
			}
			scoreBoard[p2] += (p2Total - p1Total);
		} else { // total이 같다면

			// 기본 능력치 비교
			if (player1.ability > player2.ability) { // p1 승리

				// 진 사람은 총 버린 후 이동.
				// 두개가 버려진다면 작은 것은 굳이 유지하고 있을 필요가 없음.
				if (player2.gun != 0) {
					map[player2.x][player2.y].add(player2.gun);
				}
				pList.get(p2).gun = 0;
				loserMove(p2);

				// 이긴 사람은 총이 본인 것보다 더 쎄다면 줍고, 본인이 가지고 있던 총 내려놓기.
				if (!map[player1.x][player1.y].isEmpty()) {
					if (player1.gun < map[player1.x][player1.y].peek()) {
						int tmp = pList.get(p1).gun;
						pList.get(p1).gun = map[player1.x][player1.y].poll();
						map[player1.x][player1.y].add(tmp);
					}
				}

			} else { // p2 승리 // 능력치는 같을 수 없기 때문에 else 처리
				// 진 사람은 총 버린 후 이동.		
				// 두개가 버려진다면 작은 것은 굳이 유지하고 있을 필요가 없음.
				if (player1.gun != 0) {
					map[player1.x][player1.y].add(player1.gun);
				}
				pList.get(p1).gun = 0;
				loserMove(p1);
				// 이긴 사람은 총이 본인 것보다 더 쎄다면 줍고, 본인이 가지고 있던 총 내려놓기.
				if (!map[player2.x][player2.y].isEmpty()) {
					if (player2.gun < map[player2.x][player2.y].peek()) {
						int tmp = pList.get(p2).gun;
						pList.get(p2).gun = map[player2.x][player2.y].poll();
						map[player2.x][player2.y].add(tmp);
					}
				}
			}
		}
	}

	private static void loserMove(int num) {
		Player player = pList.get(num);
		int nx = pList.get(num).x + dx[player.dir];
		int ny = pList.get(num).y + dy[player.dir];
		int check = checkPlayer(nx, ny);
		if (nx >= 1 && nx <= N && ny >= 1 && ny <= N && check == -1) { // 경계 안이고, 그 자리에 사람이 없다면
			if (!map[nx][ny].isEmpty() && map[nx][ny].peek() != 0) {
				pList.get(num).gun = map[nx][ny].poll(); // 총 획득
			}
			if (map[nx][ny].isEmpty()) {
				map[nx][ny].add(0);
			}
			pList.get(num).x = nx;
			pList.get(num).y = ny;
		} else {
			while (true) {
				pList.get(num).dir = ((pList.get(num).dir + 1) % 4); // 90도 회전
				nx = pList.get(num).x + dx[pList.get(num).dir];
				ny = pList.get(num).y + dy[pList.get(num).dir];
				check = checkPlayer(nx, ny);
				if (nx >= 1 && nx <= N && ny >= 1 && ny <= N && check == -1) {
					if (!map[nx][ny].isEmpty() && map[nx][ny].peek() != 0) {
						pList.get(num).gun = map[nx][ny].poll(); // 총 획득
					}
					pList.get(num).x = nx;
					pList.get(num).y = ny;
					break;
				}
			}
		}

	}

	private static int checkPlayer(int x, int y) {

		for (int i = 1; i <= M; i++) { // 플레이어 리스트를 돌면서 그 위치에 플레이어 존재하는 지 확인
			Player player = pList.get(i);
			if (player.x == x && player.y == y) {
				return i;
			}
		}
		return -1;
	}

	public static class Player {
		int x, y;
		int dir;
		int ability;
		int gun;

		public Player(int x, int y, int dir, int ability, int gun) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.ability = ability;
			this.gun = gun;
		}
	}
}
