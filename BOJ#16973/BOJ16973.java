import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16973 {

	static int N, M;
	static int H, W;
	static int x1, y1, x2, y2;
	static int[][] map;
	static Point[] pArray;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		x1 = Integer.parseInt(st.nextToken()) - 1;
		y1 = Integer.parseInt(st.nextToken()) - 1;

		x2 = Integer.parseInt(st.nextToken()) - 1;
		y2 = Integer.parseInt(st.nextToken()) - 1;

		bfs();
	}

	public static void bfs() {

		Queue<PointGroup> q = new LinkedList<>();
		pArray = new Point[H * W];
		boolean[][] visited = new boolean[N][M];
		int cnt = 0;
		for (int i = x1; i < H; i++) {
			for (int j = y1; j < W; j++) {
				pArray[cnt++] = new Point(i, j);
				visited[i][j] = true;
			}
		}

		q.add(new PointGroup(pArray, 0));

		while (!q.isEmpty()) {

			PointGroup now = q.poll();

			if (check(now)) {
				System.out.println(now.dist);
				break;
			}

			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {

				boolean flag = true;
				int visitCnt = 0;

				for (Point p : now.pArray) {
					nx = p.x + dx[dir];
					ny = p.y + dy[dir];
					if (isBound(nx, ny) || map[nx][ny] == 1) {
						flag = false;
						break;
					} else if (visited[nx][ny]) {
						visitCnt++;
					}
				}

				if (flag && visitCnt != H * W) {
					int idx = 0;
					for (Point p : now.pArray) {
						nx = p.x + dx[dir];
						ny = p.y + dy[dir];
						visited[nx][ny] = true;
						pArray[idx++] = new Point(nx, ny);
					}
					q.add(new PointGroup(pArray, now.dist + 1));
				}

			}
		}

	}

	public static boolean check(PointGroup pg) {
		Point p = pg.pArray[0];
		if (p.x == x2 && p.y == y2) {
			return true;
		}
		return false;
	}

	public static boolean isBound(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
			return true;
		}
		return false;
	}

	public static class PointGroup {

		Point[] pArray = new Point[H * W];
		int dist;

		public PointGroup(Point[] pArray, int dist) {
			for (int i = 0; i < H * W; i++) {
				this.pArray[i] = pArray[i];
			}
			this.dist = dist;
		}
	}

	public static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
