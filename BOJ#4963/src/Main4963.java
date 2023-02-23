import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main4963 {
	static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[][] map;
	static boolean[][] visited;
	static int w, h;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuffer sb = new StringBuffer();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) {
				break;
			}
			map = new int[h][w];
			visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			cnt = 0;

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();

		q.offer(new Point(x, y));

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int nx;
			int ny;
			for (int dir = 0; dir < 8; dir++) {
				nx = cur.x + dx[dir];
				ny = cur.y + dy[dir];

				if (nx < 0 || nx >= h || ny < 0 || ny >= w || visited[nx][ny]) {
					continue;
				}

				if (map[nx][ny] == 1) {
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny));
				}
			}
		}
		cnt++;

	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
