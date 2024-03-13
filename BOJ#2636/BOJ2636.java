
import java.util.*;
import java.io.*;

public class BOJ2636 {

	static int N, M;
	static int[][] map;
	static int totalCnt;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					totalCnt++;
				}
			}
		}

		lego();
		bw.write(sb.toString() + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	public static void airSpread() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		map[0][0] = 2;

		while (!q.isEmpty()) {
			Point now = q.poll();
			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];
				if (isBound(nx, ny) || map[nx][ny] == 1) {
					continue;
				}

				if (map[nx][ny] == 0) {
					q.add(new Point(nx, ny));
					map[nx][ny] = 2;
				}

			}
		}
	}

	public static void airClean() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		map[0][0] = 0;

		while (!q.isEmpty()) {
			Point now = q.poll();
			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];
				if (isBound(nx, ny) || map[nx][ny] == 1) {
					continue;
				}

				if (map[nx][ny] == 2) {
					q.add(new Point(nx, ny));
					map[nx][ny] = 0;
				}

			}
		}
	}

	public static void lego() {

		int hour = 0;
		int cnt = 0;
		while (totalCnt > 0) {
			airClean();
			airSpread();
			cnt = gethole();
			for (int i = 0; i < cnt; i++) {
				Point now = q.poll();
				map[now.x][now.y] = 0;
				totalCnt--;
			}
			hour++;
		}
		sb.append(hour).append("\n").append(cnt);
	}

	public static int gethole() {

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					int nx, ny;
					for (int dir = 0; dir < 4; dir++) {
						nx = i + dx[dir];
						ny = j + dy[dir];
						if (isBound(nx, ny) || map[nx][ny] == 1) {
							continue;
						}
						if (map[nx][ny] == 2) {
							q.add(new Point(i, j));
							cnt++;
							break;
						}
					}
				}
			}
		}
		return cnt;

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
