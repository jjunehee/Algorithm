package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15683 {
	public static int N, M;
	public static int[][] map;
	public static Camera[] cameras;
	public static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cameras = new Camera[8];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					switch (map[i][j]) {
					case 1:
						cameras[count] = new Camera1(i, j);
						break;
					case 2:
						cameras[count] = new Camera2(i, j);
						break;
					case 3:
						cameras[count] = new Camera3(i, j);
						break;
					case 4:
						cameras[count] = new Camera4(i, j);
						break;
					case 5:
						cameras[count] = new Camera5(i, j);
						break;
					}
					count++;
				}
			}
		}
		int Min = Integer.MAX_VALUE;
		for (int i = 0; i < Math.pow(4, count); i++) {
			Min = Math.min(Min, calculate(map, i));
		}
		System.out.println(Min);
	}

	private static int calculate(int[][] map, int i) {

		int[][] tmap = new int[N][M];
		for (int I = 0; I < N; I++) {
			tmap[I] = map[I].clone();
		}

		for (int p = 0; p < count; p++) {
			int dir = i % 4;
			cameras[p].watch(tmap, dir);
			i /= 4;
		}
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (tmap[r][c] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static abstract class Camera {
		int x;
		int y;
		int dx[] = { 1, 0, -1, 0 };
		int dy[] = { 0, 1, 0, -1 };

		public Camera(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void see(int[][] tmap, int dir) {
			int nx = x;
			int ny = y;

			while (inBound(tmap, nx, ny)) {
				tmap[nx][ny] = 7;

				nx = nx + dx[dir];
				ny = ny + dy[dir];
//				System.out.println(""+ nx + "," + ny);
			}
			
		}

		private boolean inBound(int[][] tmap, int nx, int ny) {
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || tmap[nx][ny] == 6) {
				return false;
			}

			return true;
		}

		public abstract void watch(int[][] tmap, int dir);

	}

	public static class Camera1 extends Camera {

		public Camera1(int x, int y) {
			super(x, y);
		}

		public void watch(int[][] tmap, int dir) {
			see(tmap, dir);
		}

	}

	public static class Camera2 extends Camera {

		public Camera2(int x, int y) {
			super(x, y);
		}

		public void watch(int[][] tmap, int dir) {
			see(tmap, dir);
			see(tmap, (dir + 2) % 4);
		}
	}

	public static class Camera3 extends Camera {

		public Camera3(int x, int y) {
			super(x, y);
		}

		public void watch(int[][] tmap, int dir) {
			see(tmap, dir);
			see(tmap, (dir + 1) % 4);
		}

	}

	public static class Camera4 extends Camera {

		public Camera4(int x, int y) {
			super(x, y);
		}

		public void watch(int[][] tmap, int dir) {
			see(tmap, dir);
			see(tmap, (dir + 1) % 4);
			see(tmap, (dir + 2) % 4);
		}
	}

	public static class Camera5 extends Camera {

		public Camera5(int x, int y) {
			super(x, y);
		}

		public void watch(int[][] tmap, int dir) {
			for (int i = 0; i < 4; i++) {
				see(tmap, i);
			}
		}
	}

}