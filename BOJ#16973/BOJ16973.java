import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16973 {

	static int N, M;
	static int H, W;
	static int x1, y1, x2, y2;
	static int[][] map;
	static Point[] pArray;
	static List<Point> wallList = new ArrayList<>();

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
				if (map[i][j] == 1) {
					wallList.add(new Point(i, j, 0));
				}
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

		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		visited[x1][y1] = true;
		q.add(new Point(x1, y1, 0));

		int ret = -1;
		while (!q.isEmpty()) {

			Point now = q.poll();
			if (check(now)) {
				ret = now.dist;
				break;
			}

			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];

				if (isBound(nx, ny))
					continue;
				if (isWall(nx, ny))
					continue;
				if (visited[nx][ny]) {
					continue;
				}
				if(map[nx][ny] == 1) {
					continue;
				}

				visited[nx][ny] = true;
				q.add(new Point(nx, ny, now.dist + 1));
			}
		}
		
		System.out.println(ret);

	}

	public static boolean check(Point p) {
		if (p.x == x2 && p.y == y2) {
			return true;
		}
		return false;
	}

	public static boolean isBound(int nx, int ny) {
		if (nx < 0 || nx + H - 1 >= N || ny < 0 || ny + W - 1 >= M) {
			return true;
		}
		return false;
	}

	public static boolean isWall(int nx, int ny) {

		for (int i = 0; i < wallList.size(); i++) {

			int wallX = wallList.get(i).x;
			int wallY = wallList.get(i).y;

			if (nx <= wallX && nx + H - 1 >= wallX && ny <= wallY && ny + W - 1 >= wallY) {
				return true;
			}
		}
		return false;
	}

	public static class Point {
		int x, y;
		int dist;

		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}
