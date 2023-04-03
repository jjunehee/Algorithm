import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

	static int[][] map;
	static int N, M;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static boolean[][] visited;
	static int min = Integer.MAX_VALUE;

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

		pickWall(0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void pickWall(int cnt) {
		if (min == N + M - 1) {
			return;
		}

		if (cnt == 1) {
			int result = simulation();
			if (result != -1) {
				min = Math.min(min, result);
			}
			return;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 1) {
					map[i][j] = 0; // 벽 부수기
					pickWall(cnt + 1);
					map[i][j] = 1; // 벽 다시 복구
				}
			}
		}

	}

	private static int simulation() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(1, 1, 1));

		visited = new boolean[N + 1][M + 1];
		while (!q.isEmpty()) {

			Point now = q.poll();
			if (now.x == N && now.y == M) {
				return now.dist;
			} else if (now.dist > min) {
				return -1;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nx = now.x + dx[dir];
				int ny = now.y + dy[dir];

				if (nx >= 1 && nx <= N && ny >= 1 && ny <= M && !visited[nx][ny] && map[nx][ny] == 0) {

					visited[nx][ny] = true;
					q.add(new Point(nx, ny, now.dist + 1));
				}
			}
		}
		return -1;

	}

	public static class Point {
		int x;
		int y;
		int dist;

		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

}
