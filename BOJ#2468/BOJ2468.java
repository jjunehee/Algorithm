import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2468 {

	static int N;
	static int[][] map;
	static int[][] copyMap;
	static int maxHeight = Integer.MIN_VALUE;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}

		int maxSafeZone = Integer.MIN_VALUE;
		for (int rain = 1; rain <= maxHeight; rain++) {
			copyMap = deepCopy(map);
			raining(rain);
			maxSafeZone = Math.max(maxSafeZone, checkSafeZone());
		}

		System.out.println(maxSafeZone);

	}

	public static int[][] deepCopy(int[][] map) {
		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

		return copyMap;
	}

	public static void raining(int rain) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copyMap[i][j] < rain) {
					copyMap[i][j] = 1;
				} else {
					copyMap[i][j] = 0;
				}
			}
		}
	}

	public static int checkSafeZone() {

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copyMap[i][j] == 0) {

					Queue<Pos> q = new LinkedList<>();
					q.add(new Pos(i, j));
					copyMap[i][j] = 1;

					while (!q.isEmpty()) {
						Pos now = q.poll();

						for (int dir = 0; dir < 4; dir++) {
							int nx = now.x + dx[dir];
							int ny = now.y + dy[dir];

							if (isBound(nx, ny) || copyMap[nx][ny] == 1) {
								continue;
							}

							copyMap[nx][ny] = 1;
							q.add(new Pos(nx, ny));

						}
					}
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static boolean isBound(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return true;
		} else {
			return false;
		}
	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
