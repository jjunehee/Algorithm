import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution16236 {

	static int[][] map;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };
	static Fish shark;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		// map
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Fish(i, j, 0, 2);
				}
			}
		}

		// Game Start
		int result = gameStart();

		System.out.println(result);

	}

	public static int gameStart() {

		int time = 0;
		while (true) {
			Pos fish = check();
			if (fish == null) {
				break;
			}

			int T = Math.abs(shark.x - fish.x) + Math.abs(shark.y - fish.y);
			time += T;
			
			map[fish.x][fish.y] = 0;
			shark.eat++;
			shark.x = fish.x;
			shark.y = fish.y;
			
			if (shark.eat == shark.size) {
				shark.size++;
				shark.eat = 0;
			}
		}

		return time;
	}

	public static Pos check() {

		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(shark.x, shark.y));
		boolean[][] visited = new boolean[N][N];

		while (!q.isEmpty()) {
			Pos now = q.poll();
			visited[now.x][now.y] = true;
			int nx, ny;

			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];
				if (boundCheck(nx, ny) && !visited[nx][ny]) {
					if (map[nx][ny] == 0 || map[nx][ny] == shark.size) { // 상어가 지나갈 수는 있는 칸
						q.add(new Pos(nx, ny));
					} else if (map[nx][ny] < shark.size) { // 먹을 수 있는 물고기 발견
						return new Pos(nx, ny);
					}
				}
			}
		}

		return null;
	}

	public static boolean boundCheck(int nx, int ny) {

		if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return false;
		}

		return true;
	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Fish {
		int x, y;
		int eat;
		int size;

		public Fish(int x, int y, int eat, int size) {
			this.x = x;
			this.y = y;
			this.eat = eat;
			this.size = size;
		}
	}
}
