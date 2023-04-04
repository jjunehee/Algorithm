import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class IwantBread {

	static int[][] map;
	static ArrayList<Point> storeList;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };
	static int N, M;
	static boolean[][] visited;
	static int ret;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 사람 수

		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		storeList = new ArrayList<>();
		storeList.add(new Point(-1, -1)); // 인덱스 0 값
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			storeList.add(new Point(x, y));
		}

		visited = new boolean[N + 1][N + 1];
		simulation();
		System.out.println(ret);
	}

	private static void simulation() {

		Queue<Point> q = new LinkedList<>();
		int time = 1;
		while (true) {
			Point person = null;
			if (time <= M) {
				Point baseCamp = searchBasecamp(time);
				person = new Point(baseCamp.x, baseCamp.y, storeList.get(time).x, storeList.get(time).y);
				visited[baseCamp.x][baseCamp.y] = true;
			}

			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point now = q.poll();
				if (!visited[now.dX][now.dY]) {
					if (now.x == now.dX && now.y == now.dY) {
						visited[now.dX][now.dY] = true;
						continue;
					}
					for (int dir = 0; dir < 4; dir++) {
						int nx = now.x + dx[dir];
						int ny = now.y + dy[dir];
						if (nx >= 1 && nx <= N && ny >= 1 && ny <= N && !visited[nx][ny]) {
							q.add(new Point(nx, ny, now.dX, now.dY));
						}
					}
				}
			}

			if (test()) {
				ret = time-1;
				break;
			}

			if (person != null) {
				q.add(person);
			}
			time++;

		}
	}

	private static boolean test() {

		int count = 0;
		for (int i = 1; i <= M; i++) {
			if (visited[storeList.get(i).x][storeList.get(i).y]) {
				count++;
			}
		}
		if (count == M) {
			return true;
		} else {
			return false;
		}
	}

	private static Point searchBasecamp(int time) {
		Queue<Point> q = new LinkedList<>();

		q.add(storeList.get(time));

		while (!q.isEmpty()) {
			Point now = q.poll();
			if (map[now.x][now.y] == 1) { // 가장 가까운 베이스캠프 발견 (bfs이므로)
				return now;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nx = now.x + dx[dir];
				int ny = now.y + dy[dir];
				if (nx >= 1 && nx <= N && ny >= 1 && ny <= N && !visited[nx][ny]) {
					q.add(new Point(nx, ny));
				}
			}
		}

		return new Point(-1, -1); // 가까운 베이스캠프 없음. // 하지만 아마 그럴일 없을거임.
	}

	public static class Point {
		int x, y;
		int dX, dY;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int dX, int dY) {
			this.x = x;
			this.y = y;
			this.dX = dX;
			this.dY = dY;
		}
	}
}
