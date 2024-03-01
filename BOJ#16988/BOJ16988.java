import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16988 {

	static int N, M;
	static int[][] map;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static List<Point> blackList = new ArrayList<>();
	static List<Point> blankList = new ArrayList<>();
	static int[] pick;
	static int max = Integer.MIN_VALUE;

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
				if (map[i][j] == 2) {
					blackList.add(new Point(i, j));
				}
			}
		}

		getBlank();

		pick = new int[2];
		comb(0, 0);

		System.out.println(max == Integer.MIN_VALUE ? 0 : max);
	}

	public static void getBlank() {

		boolean[][] visited = new boolean[N][M];

		for (Point black : blackList) {

			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = black.x + dx[dir];
				ny = black.y + dy[dir];

				if (isBound(nx, ny) || visited[nx][ny] || map[nx][ny] == 2) {
					continue;
				}

				if (map[nx][ny] == 0) {
					blankList.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}

		}
	}

	public static boolean isBound(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
			return true;
		}
		return false;
	}

	public static void comb(int idx, int cnt) {

		if (cnt == 2) {
			for (int num : pick) {
				Point p = blankList.get(num);
				map[p.x][p.y] = 1;
			}
			int ret = calculate();

			max = Math.max(max, ret);

			for (int num : pick) {
				Point p = blankList.get(num);
				map[p.x][p.y] = 0;
			}
			return;
		}

		for (int i = idx; i < blankList.size(); i++) {
			pick[cnt] = i;
			comb(i, cnt + 1);
		}
	}

	public static int calculate() {

		int ret = 0;
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2 && !visited[i][j]) {
					Queue<Point> q = new LinkedList<>();
					q.add(new Point(i, j));
					visited[i][j] = true;

					boolean flag = true;
					int cnt = 0;
					while (!q.isEmpty()) {

						Point now = q.poll();

						if (map[now.x][now.y] == 0) {
							flag = false;
						} else if (map[now.x][now.y] == 2) {
							cnt++;
						}

						int nx, ny;
						for (int dir = 0; dir < 4; dir++) {
							nx = now.x + dx[dir];
							ny = now.y + dy[dir];
							if (isBound(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) {
								continue;
							}

							q.add(new Point(nx, ny));
							visited[nx][ny] = true;
						}
					}

					if (flag) {
						ret += cnt;
					}

				}
			}
		}

		return ret;
	}

	public static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
