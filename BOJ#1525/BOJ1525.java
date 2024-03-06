
import java.util.*;
import java.io.*;

public class BOJ1525 {

	static int[][] map;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static HashMap<String, Integer> visited = new HashMap<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[3][3];
		int blankX = 0;
		int blankY = 0;
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					blankX = i;
					blankY = j;
				}
			}
		}

		bfs(blankX, blankY);
	}

	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y, map, 0));

		save(map);
		boolean flag = false;
		while (!q.isEmpty()) {

			Point now = q.poll();

			if (check(now.tmpMap)) {
				flag = true;
				System.out.println(now.cnt);
				break;
			}

			int nx, ny;
			int tmp;
			for (int dir = 0; dir < 4; dir++) {
				nx = now.x + dx[dir];
				ny = now.y + dy[dir];

				if (isBound(nx, ny)) {
					continue;
				}

				tmp = now.tmpMap[nx][ny];
				now.tmpMap[nx][ny] = now.tmpMap[now.x][now.y];
				now.tmpMap[now.x][now.y] = tmp;

				if (!checkVisited(now.tmpMap)) {
					save(now.tmpMap);
					q.add(new Point(nx, ny, now.tmpMap, now.cnt + 1));
				}

				now.tmpMap[nx][ny] = tmp;
				now.tmpMap[now.x][now.y] = 0;
			}
		}

		if (!flag) {
			System.out.println(-1);
		}

	}

	public static boolean checkVisited(int[][] map) {
		String str = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				str += map[i][j] + "";
			}
		}

		int ret = visited.getOrDefault(str, 0);
		if (ret == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static void save(int[][] map) {

		String str = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				str += map[i][j] + "";
			}
		}
		visited.put(str, 1);
	}

	public static boolean check(int[][] map) {
		int value = 1;
		int checkX = 0;
		int checkY = 0;

		outerloop: for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j] != value++) {
					checkX = i;
					checkY = j;
					break outerloop;
				}
			}
		}

		if (checkX == 2 && checkY == 2) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isBound(int nx, int ny) {
		if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) {
			return true;
		}
		return false;
	}

	public static class Point {
		int x, y;
		int[][] tmpMap = new int[3][3];
		int cnt;

		public Point(int x, int y, int[][] tmpMap, int cnt) {
			this.x = x;
			this.y = y;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					this.tmpMap[i][j] = tmpMap[i][j];
				}
			}
			this.cnt = cnt;
		}
	}
}
