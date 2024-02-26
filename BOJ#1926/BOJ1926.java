import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1926 {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int cnt, maxSize;
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

		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j);
					cnt++;
				}
			}
		}

		System.out.println(cnt);
		System.out.println(maxSize);

	}

	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;

		int size = 1;
		while (!q.isEmpty()) {
			Point now = q.poll();

			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];
				if (isBound(nx, ny) || visited[nx][ny]) {
					continue;
				}

				if (map[nx][ny] == 1) {
					visited[nx][ny] = true;
					q.add(new Point(nx, ny));
					size++;
				}
			}
		}

		maxSize = Math.max(maxSize, size);
	}

	public static boolean isBound(int nx, int ny) {

		if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
			return true;
		}
		return false;
	}

	public static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
