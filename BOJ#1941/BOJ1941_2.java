
import java.util.*;
import java.io.*;

public class BOJ1941_2 {

	static char[][] map;
	static char[][] copyMap;
	static List<Point> sList = new ArrayList<>();
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int ret;
	static Point[] pick;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[5][5];
		copyMap = new char[5][5];

		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
				copyMap[i][j] = '.';
				sList.add(new Point(i, j));
			}
		}
		pick = new Point[7];
		pickS(0, 0, 0);
		System.out.println(ret);

	}

	public static void pickS(int idx, int cnt, int sCnt) {

		if (cnt - sCnt > 3) {
			return;
		}

		if (cnt == 7) {
			for (Point p : pick) {
				copyMap[p.x][p.y] = 'T';
			}
			search();
			for (Point p : pick) {
				copyMap[p.x][p.y] = '.';
			}
			return;
		}

		for (int i = idx; i < 25; i++) {
			Point point = sList.get(i);
			pick[cnt] = point;
			if (map[point.x][point.y] == 'S') {
				pickS(i + 1, cnt + 1, sCnt + 1);
			} else {
				pickS(i + 1, cnt + 1, sCnt);
			}
		}
	}

	public static void search() {

		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[5][5];
		outerLoop: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (copyMap[i][j] == 'T') {
					q.add(new Point(i, j));
					visited[i][j] = true;
					break outerLoop;
				}
			}
		}

		int cnt = 1;
		while (!q.isEmpty()) {
			Point now = q.poll();

			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];
				if (isBound(nx, ny) || copyMap[nx][ny] == '.' || visited[nx][ny]) {
					continue;
				}
				visited[nx][ny] = true;
				q.add(new Point(nx, ny));
				cnt++;
			}
		}

		if (cnt == 7) {
			ret++;
		}
	}

	public static boolean isBound(int nx, int ny) {
		if (nx >= 5 || nx < 0 || ny >= 5 || ny < 0) {
			return true;
		} else {
			return false;
		}
	}

	public static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
