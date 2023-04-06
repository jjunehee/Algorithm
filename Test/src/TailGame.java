import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TailGame {

	static int[][] map;
	static int N, M, K;
	static ArrayList<Point>[] team;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static boolean[][] visited;
	static int[] tail;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		simulation();

	}

	private static void simulation() {
		// 맵 그린 뒤 들어왔다.

		team = new ArrayList[3];
		tail = new int[3]; // i팀의 꼬리 인덱스
		for (int i = 1; i <= 2; i++) {
			team[i] = new ArrayList<>();
		}

		int cnt = 1;
		// 팀 정보 넣자.
		// 팀의 머리부분들이 team리스트에 첫번째에 add.
		// 팀 멤버들 추가해야함.
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					team[cnt].add(new Point(i, j, 0));
					teamMake(i, j, cnt, 0);
					cnt++;
				}
			}
		}

		// team구성이 완료된 상태.
		// tail에는 꼬리 인덱스 저장되어 있음.
		gameStart();
	}

	private static void gameStart() {

		int round = 1;
		while (round <= K) {

			// 모든 팀 이동
			move();

			// 공 발사
			int result = ball();

			// 점수 획득
			score(result);

			// 방향 바꾸기
			changeDir();

			break;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		totalCnt();
	}

	private static void totalCnt() {

	}

	private static void move() {

		visited = new boolean[N][N];

		for (int i = 1; i <= M; i++) { // 1팀부터 M팀까지 이동할껀데~
			int idx = tail[i];

			while (idx >= 0) {
				Point now = team[i].get(idx); // 꼬리
				for (int dir = 0; dir < 4; dir++) {
					int nx = now.x + dx[dir];
					int ny = now.y + dy[dir];

					if (idx == 0) { // 마지막으로 head를 앞에 4로 움직일때.
						if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] != 0) {
							visited[nx][ny] = true;
							team[i].get(idx).x = nx;
							team[i].get(idx).y = ny;
						}
					} else if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] != 0
							&& map[nx][ny] != 4) {
						visited[nx][ny] = true;
						team[i].get(idx).x = nx;
						team[i].get(idx).y = ny;
					}
				}
				idx--;
			}
		}

		System.out.println(team[1].size());
		for (int i = 0; i < team[1].size(); i++) {
			System.out.println(team[1].get(i).x + " " + team[1].get(0).y);
		}

		System.out.println("=====================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && map[i][j] != 4) {
					map[i][j] = 4;
				}
			}
		}

		for (int i = 1; i <= M; i++) {
			int cnt = 1;
			int size = team[i].size();
			for (Point p : team[i]) {
				if (cnt == 1 || cnt == size) {
					map[p.x][p.y] = cnt++;
				} else {
					map[p.x][p.y] = cnt;
				}
			}
		}
	}

	private static int ball() {

		return -1;
	}

	private static void score(int num) {

	}

	private static void changeDir() {

	}

	private static void teamMake(int x, int y, int num, int cnt) {

		visited[x][y] = true;
		Point now = team[num].get(cnt);

		// DFS를 이용해서 팀멤버 추가하기
		for (int dir = 0; dir < 4; dir++) {
			int nx = now.x + dx[dir];
			int ny = now.y + dy[dir];
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != 0 && !visited[nx][ny]) {
				if (map[nx][ny] == 2) {
					team[num].add(new Point(nx, ny, 0));
					teamMake(nx, ny, num, cnt + 1);
				} else if (map[nx][ny] == 3) {
					team[num].add(new Point(nx, ny, 0));
					teamMake(nx, ny, num, cnt + 1);
					tail[num] = cnt + 1;
					return;
				}
			}
		}
	}

	public static class Point {
		int x, y;
		int score;

		public Point(int x, int y, int score) {
			this.x = x;
			this.y = y;
			this.score = score;
		}
	}
}
