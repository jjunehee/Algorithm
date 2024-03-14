import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {

	static int M, N, H;
	static int[][][] map;

	static Queue<Point> ripeQ = new LinkedList<>();
	static int notRipe;
	static int[] dx = { 1, 0, -1, 0, 0, 0 };
	static int[] dy = { 0, -1, 0, 1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N][M];

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					map[h][n][m] = Integer.parseInt(st.nextToken());
					if (map[h][n][m] == 0) {
						notRipe++;
					} else if (map[h][n][m] == 1) {
						ripeQ.add(new Point(h, n, m, 0));
					}
				}
			}
		}

		if (notRipe != 0) {
			System.out.print(bfs());
		} else {
			System.out.print(0);
		}

	}

	public static int bfs() {

		while (!ripeQ.isEmpty()) {

			Point now = ripeQ.poll();

			int nx, ny, nz;
			for (int dir = 0; dir < 6; dir++) {
				nx = now.n + dx[dir];
				ny = now.m + dy[dir];
				nz = now.h + dz[dir];
				if (isBound(nx, ny, nz) || map[nz][nx][ny] == -1 || map[nz][nx][ny] == 1) {
					continue;
				}

				if (map[nz][nx][ny] == 0) {
					ripeQ.add(new Point(nz, nx, ny, now.time + 1));
					map[nz][nx][ny] = 1;
					notRipe--;
				}
				
				if (notRipe == 0) {
					return now.time + 1;
				}

			}
			
		}

		return -1;
	}

	public static boolean isBound(int nx, int ny, int nz) {

		if (nx < 0 || nx >= N || ny < 0 || ny >= M || nz < 0 || nz >= H) {
			return true;
		}
		return false;
	}

	public static class Point {
		int h, n, m;
		int time;

		public Point(int h, int n, int m, int time) {
			this.h = h;
			this.n = n;
			this.m = m;
			this.time = time;
		}
	}
}
