import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2667 {
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int[][] map;
	static boolean[][] visited;
	static int w, h;
	static int cnt;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					int value = bfs(i, j);
					list.add(value);
					cnt++;
				}
			}
		}
		
		sb.append(cnt).append("\n");
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append("\n");
		}
	}

	private static int bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();

		q.offer(new Point(x, y));

		int count = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];

				if (nx < 0 || nx >= h || ny < 0 || ny >= w || visited[nx][ny]) {
					continue;
				}

				if (map[nx][ny] == 1) {
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny));
					count++;
				}
			}
		}

		return count;
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
