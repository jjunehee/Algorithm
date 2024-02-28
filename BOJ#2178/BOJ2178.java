import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {

	static int[][] map;
	static int N, M;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j - 1) - '0';
			}
		}

		BFS();

	}

	public static void BFS() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N + 1][M + 1];

		q.add(new Point(1, 1, 1));
		visited[1][1] = true;

		while (!q.isEmpty()) {

			Point now = q.poll();

			if (now.x == N && now.y == M) {
				System.out.println(now.dist);
				break;
			}
			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];

				if (isBound(nx, ny) || visited[nx][ny]) {
					continue;
				}
				if (map[nx][ny] == 1) {
					visited[nx][ny] = true;
					q.add(new Point(nx, ny, now.dist + 1));
				}

			}
		}

	}

	public static boolean isBound(int nx, int ny) {

		if (nx < 1 || nx > N || ny < 1 || ny > M) {
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
