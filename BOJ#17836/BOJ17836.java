
// 공주님을 구해라!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17836 {

	static int N, M, T;
	static int[][] map;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();
		if(min == Integer.MAX_VALUE) {
			System.out.println("Fail");
		} else if(min > T) {
			System.out.println("Fail");
		} else {
			System.out.println(min);
		}
	}

	public static void bfs() {
		boolean[][] visited = new boolean[N + 1][M + 1];
		Queue<Point> q = new LinkedList<>();

		q.add(new Point(1, 1, 0));
		visited[1][1] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();

			if (now.x == N && now.y == M) {
				min = Math.min(min, now.dist);
			} else if (map[now.x][now.y] == 2) {
				int direct = Math.abs(now.x - N) + Math.abs(now.y - M);
				min = Math.min(min, now.dist + direct);
			}

			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];

				if (isBound(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) {
					continue;
				}

				visited[nx][ny] = true;
				q.add(new Point(nx, ny, now.dist + 1));

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
