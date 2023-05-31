import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class aProblemRobot {

	static int N, M;
	static int[][] map;
	static int[][] copyMap;

	static int[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			copyMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 1)
						map[i][j] = -9;
					else
						map[i][j] = -2;
				}
			}

			int max = Integer.MIN_VALUE;
			int res = 0;
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < N - 1; j++) {

					if (map[i][j] == -2) {
						for (int dir = 0; dir < 4; dir++) {
							visited = new int[N][N];
							res = simulation(i, j, dir);
							max = Math.max(max, res);
						}
					}
				}
			}
			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static int simulation(int x, int y, int dir) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

		int[] next = null;
		int day = 0;
		int res = 0;
		int curX = x;
		int curY = y;
		int curDir = dir;
		while (day++ < M) {

			next = getNext(curX, curY, curDir);

			// 오전
			if (copyMap[curX][curY] == -2) {
				if (next != null) {
					visited[curX][curY]++;
					copyMap[curX][curY] = -1;
				}
			} else if (copyMap[curX][curY] >= visited[curX][curY] + 3) {
				res++;
				copyMap[curX][curY] = -2;
			}

			// 오후
			if (next != null) {
				curX = next[0];
				curY = next[1];
				curDir = next[2];
			}
			// 성장
			grow();
		}
		return res;
	}

	public static int[] getNext(int x, int y, int dir) {

		int[] nextPos = null;
		int[] a = { 1, 0, 3, 2 };
		for (int i = 0; i < 4; i++) {

			nextPos = search(x, y, (dir + a[i]) % 4);
			if (nextPos != null) {
				return nextPos;
			}
		}
		return null;
	}

	public static int[] search(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if (copyMap[nx][ny] == -2 || copyMap[nx][ny] >= visited[nx][ny] + 3) {
			return new int[] { nx, ny, dir };
		}
		return null;
	}

	public static void grow() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(copyMap[i][j] > -2) {
					copyMap[i][j]++;
				}
			}
		}
	}
}
