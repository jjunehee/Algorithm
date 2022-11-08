import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution44 {
	static int[][] map;
	static int N, M;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static final int W = 2;
	static final int B = 1;
	static String[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new String[T];
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N + 1][N + 1];

			map[N / 2][N / 2] = 2;
			map[N / 2][N / 2 + 1] = 1;
			map[N / 2 + 1][N / 2] = 1;
			map[N / 2 + 1][N / 2 + 1] = 2;

			Queue<order> q = new LinkedList<>();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int WB = Integer.parseInt(st.nextToken());
				q.add(new order(x, y, WB));
			}

			while (!q.isEmpty()) {
				order now = q.poll();
				if (now.wb == B) {
					map[now.x][now.y] = B;
					for (int dir = 0; dir < 8; dir++) {
						int aroundX = now.x + dx[dir];
						int aroundY = now.y + dy[dir];
						if (aroundX < 1 || aroundX > N || aroundY < 1 || aroundY > N) {
							continue;
						}
						if (map[aroundX][aroundY] == W) {
							changeColor(aroundX, aroundY, B, dir);
						}
					}
				} else {
					map[now.x][now.y] = W;
					for (int dir = 0; dir < 8; dir++) {
						int aroundX = now.x + dx[dir];
						int aroundY = now.y + dy[dir];
						if (aroundX < 1 || aroundX > N || aroundY < 1 || aroundY > N) {
							continue;
						}
						if (map[aroundX][aroundY] == B) {
							changeColor(aroundX, aroundY, W, dir);
						}
					}
				}
			}
			int count1 = 0;
			int count2 = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 1) {
						count1++;
					} else if (map[i][j] == 2) {
						count2++;
					}
				}
			}
			answer[t] = String.valueOf(count1) +" " +  String.valueOf(count2);
		}

		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}

	}

	public static void changeColor(int x, int y, int color, int dir) {
		Queue<order> t = new LinkedList<>();
		t.add(new order(x, y, color));
		int nX = x;
		int nY = y;
		boolean flag = false;
		while (true) {

			nX = nX + dx[dir];
			nY = nY + dy[dir];

			if (nX < 1 || nX > N || nY < 1 || nY > N || map[nX][nY] == 0) {
				break;
			}
			if (map[nX][nY] == color) {
				flag = true;
				break;
			} else {
				t.add(new order(nX, nY, color));
			}

		}
		while (flag && !t.isEmpty()) {
			order w = t.poll();
			int X = w.x;
			int Y = w.y;
			map[X][Y] = color;
		}

	}

	public static class order {
		int x;
		int y;
		int wb;

		public order(int x, int y, int wb) {
			this.x = x;
			this.y = y;
			this.wb = wb;
		}
	}
}
