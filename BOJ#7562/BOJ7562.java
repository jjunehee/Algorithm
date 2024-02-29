import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 2, 1, -1, -2, -2, -1, 1, 2 };
	static int[] dy = { -1, -2, -2, -1, 1, 2, 2, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());

			Point Start = new Point(x1, y1, 0);

			st = new StringTokenizer(br.readLine());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			Point End = new Point(x2, y2, 0);

			int ret = bfs(Start, End);

			if (t == T) {
				sb.append(ret);
			} else {
				sb.append(ret).append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	public static int bfs(Point start, Point end) {

		Queue<Point> q = new LinkedList<>();
		q.add(start);
		visited[start.x][start.y] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();

			if (now.x == end.x && now.y == end.y) {
				return now.dist;
			}

			int nx, ny;
			for (int dir = 0; dir < 8; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];
				if (isBound(nx, ny) || visited[nx][ny]) {
					continue;
				}

				q.add(new Point(nx, ny, now.dist + 1));
				visited[nx][ny] = true;
			}
		}

		return -1;
	}

	public static boolean isBound(int nx, int ny) {

		if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return true;
		}

		return false;
	}

	public static class Point {
		int x, y, dist;

		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}
