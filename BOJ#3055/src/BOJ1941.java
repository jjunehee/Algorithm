import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1941 {

	static String[][] map;
	static ArrayList<Point> sList = new ArrayList<>();
	static Point[] pickN;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int ret;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new String[5][5];
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = String.valueOf(str.charAt(j));
				if (map[i][j].equals("S")) {
					sList.add(new Point(i, j));
				}
			}
		}

		// 칠공주 멤버가 될 수 있는 4명~7명까지 뽑기
		for (int n = 4; n <= 7; n++) {
			pickN = new Point[n];
			pick(0, 0, n);
		}
		System.out.println(ret);

	}

	private static void pick(int idx, int cnt, int N) {
		if (cnt == N) {
			BFS(pickN, N);
			return;
		}

		for (int i = idx; i < sList.size(); i++) {
			pickN[cnt] = sList.get(i);
			pick(i + 1, cnt + 1, N);
		}
	}

	private static void BFS(Point[] pick, int N) {
		System.out.println("========================");
		
		Queue<Point> q = new LinkedList<>();
		String[][] tempMap = new String[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				tempMap[i][j] = ".";
			}
		}
		for (Point S : pick) {
			tempMap[S.x][S.y] = "S";
		}
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				System.out.print(tempMap[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println("==========");
		q.add(new Point(pick[0].x, pick[0].y, 1, 0));

		boolean[][] visited = new boolean[5][5];
		while (!q.isEmpty()) {
			Point now = q.poll();
			if (now.cnt == 7) {
				if (now.sCnt == N) {
					System.out.println("t");
					ret++;
					return;
				}
			}
			for (int dir = 0; dir < 4; dir++) {
				int nx = now.x + dx[dir];
				int ny = now.y + dy[dir];
				if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					if (tempMap[nx][ny].equals("S")) {
						
						q.add(new Point(nx, ny, now.sCnt + 1, now.cnt + 1));
						
					} else if(!map[nx][ny].equals("S")) {
						
						q.add(new Point(nx, ny, now.sCnt, now.cnt + 1));
						
					}
				}
			}
		}
	}

	public static class Point {
		int x, y;
		int sCnt;
		int cnt;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int sCnt, int cnt) {
			this.x = x;
			this.y = y;
			this.sCnt = sCnt;
			this.cnt = cnt;
		}
	}
}
