import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution16236 {

	static int[][] map;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };
	static Shark shark;
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
					shark = new Shark(i, j, 0, 2);
					map[i][j] = 0;
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

			time += fish.dist;
			
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
		PriorityQueue<Pos> fishList = new PriorityQueue<>();

		q.add(new Pos(shark.x, shark.y, 0));
		boolean[][] visited = new boolean[N][N];
		visited[shark.x][shark.y] = true;


		while (!q.isEmpty()) {
			Pos now = q.poll();
			int nx, ny;

			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];
				if (boundCheck(nx, ny) && !visited[nx][ny]) {
					if (map[nx][ny] == 0 || map[nx][ny] == shark.size) { // 상어가 지나갈 수는 있는 칸
						visited[nx][ny] = true;
						q.add(new Pos(nx, ny, now.dist + 1));
					} else if (map[nx][ny] < shark.size) { // 먹을 수 있는 물고기 발견
						visited[nx][ny] = true;
						fishList.offer(new Pos(nx, ny, now.dist + 1));
					}
				}
			}
		}

		return fishList.poll();
	}

	public static boolean boundCheck(int nx, int ny) {

		if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return false;
		}

		return true;
	}

	public static class Pos implements Comparable<Pos> {
		int x, y;
		int dist;

		public Pos(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.dist == o.dist) {
				if (this.x == o.x) {
					return this.y - o.y;
				} else {
					return this.x - o.x;
				}
			} else {
				return this.dist - o.dist;
			}
		}

	}

	public static class Shark {
		int x, y;
		int eat;
		int size;

		public Shark(int x, int y, int eat, int size) {
			this.x = x;
			this.y = y;
			this.eat = eat;
			this.size = size;
		}

	}
}
