import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea4193 {
	static int[][] map;
	static int N, A, B, C, D;
	static ArrayList<Hurricane> hList;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			StringTokenizer st;
			hList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 2) {
						hList.add(new Hurricane(i, j));
					}
				}
			}

			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			sb.append(simulation()).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int simulation() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(A, B, 0));

		boolean[][] visited = new boolean[N][N];
		visited[A][B] = true;
		while (!q.isEmpty()) {
			Point now = q.poll();
			if (now.x == C && now.y == D) {
				return now.time;
			}

			if (now.time % 3 == 2) {
				for (Hurricane h : hList) {
					map[h.x][h.y] = 0;
				}
			} else {
				for (Hurricane h : hList) {
					map[h.x][h.y] = 2;
				}
			}
			for (int dir = 0; dir < 4; dir++) {
				int nx = now.x + dx[dir];
				int ny = now.y + dy[dir];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] == 2) {
					q.add(new Point(now.x, now.y, now.time + 1));
				} else if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] != 1) {
					visited[nx][ny] = true;
					q.add(new Point(nx, ny, now.time + 1));
				}
			}
		}
		return -1;
	}

	public static class Point {
		int x;
		int y;
		int time;

		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static class Hurricane {
		int x;
		int y;

		public Hurricane(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
