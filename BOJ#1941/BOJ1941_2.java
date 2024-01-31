
import java.util.*;
import java.io.*;

public class BOJ1941_2 {

	static char[][] map;
	static List<Point> sList = new ArrayList<>();
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int ret;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[5][5];

		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					sList.add(new Point(i, j, 1, 1));
				}
			}
		}
		search();
		System.out.println(ret);
	}

	public static void search() {

		for (Point S : sList) {

			Queue<Point> q = new LinkedList<>();
			q.add(S);
			boolean[][] visited = new boolean[5][5];

			while (!q.isEmpty()) {
				Point now = q.poll();

				if (now.totalCnt == 7) {
					if(now.sCnt >= 4) {
						ret++;
						for (int i = 0; i < 5; i++) {
							for (int j = 0; j < 5; j++) {
								if(visited[i][j]) {
									System.out.print("T");
								}else {
									System.out.print("F");
								}
							}
							System.out.println();
						}
						System.out.println();
					}
					break;
				}
				int nx, ny;
				for (int dir = 0; dir < 4; dir++) {
					nx = now.x + dx[dir];
					ny = now.y + dy[dir];

					if (isBound(nx, ny) || visited[nx][ny]) {
						continue;
					}

					if (map[nx][ny] == 'S') {
						q.add(new Point(nx, ny, now.sCnt + 1, now.totalCnt + 1));
					} else {
						q.add(new Point(nx, ny, now.sCnt, now.totalCnt + 1));
					}
					visited[nx][ny] = true;
				}

			}
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
		int sCnt;
		int totalCnt;

		public Point(int x, int y, int sCnt, int totalCnt) {
			this.x = x;
			this.y = y;
			this.sCnt = sCnt;
			this.totalCnt = totalCnt;
		}
	}
}
