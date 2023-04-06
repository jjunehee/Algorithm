import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FuckingTree {

	static int[][] map;
	static ArrayList<Point> treeList = new ArrayList<>();
	static int n, m, k, c;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int[][] spreadTree;
	static int mx, my;
	static int[] mdx = { 1, -1, -1, 1 };
	static int[] mdy = { 1, 1, -1, -1 };
	static int[][] mInfo;
	static int year;
	static boolean[][] ban;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != -1) {
					treeList.add(new Point(i, j));
				}
			}
		}

		simulation();

	}

	private static void simulation() {

		year = 1;
		while (year <= m) {

			// 나무 성장
			treeGrow();

			// 나무 번식
			treeSpread();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			// 체조제 처리
			medicine();

			// 삭제할 제초제 처리

			break;
		}

	}

	private static void medicine() {
		Queue<Point> q = new LinkedList<>();
		int max = Integer.MIN_VALUE;
		boolean[][] visited2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != 0 && map[i][j] != -1) {

					int sum = 0;
					q.add(new Point(i, j));
					visited2 = new boolean[n][n];
					visited2[i][j] = true;
					System.out.println(i + "시작 " + j);
					while (!q.isEmpty()) {
						Point now = q.poll();
						for (int dir = 0; dir < 4; dir++) {
							int nx = now.x + mdx[dir];
							int ny = now.y + mdy[dir];
							// 나무라면
//						System.out.println( i + " " + j);
							if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != 0 && map[nx][ny] != -1
									&& !visited2[nx][ny]) {
								visited2[nx][ny] = true;
								System.out.println(nx + " " + ny);
								sum += map[nx][ny];
								q.add(new Point(nx, ny));
							}
						}
					}
					System.out.println(sum);
					System.out.println("============");
//				if (map[i][j] > max) {
//					max = map[i][j];
//					mx = i;
//					my = j;
//				}
				}
			}
		}

		Queue<Point> mq = new LinkedList<>();

		mInfo = new int[n][n];

		ban = new boolean[n][n];
		map[mx][my] = 0;
		mInfo[mx][my] = year + c;
		mq.add(new Point(mx, my));

		while (!mq.isEmpty()) {
			Point now = mq.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = now.x + mdx[dir];
				int ny = now.y + mdy[dir];
				// 나무라면
				if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != 0 && map[nx][ny] != -1) {
					mInfo[nx][ny] = year + c;
					map[nx][ny] = 0;
					ban[nx][ny] = true;
				}
			}
		}
	}

	private static void treeSpread() {

		spreadTree = new int[n][n];
		for (int i = 0; i < treeList.size(); i++) {
			Point tree = treeList.get(i);

			int cnt = 0;
			for (int dir = 0; dir < 4; dir++) {
				int nx = tree.x + dx[dir];
				int ny = tree.y + dy[dir];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == 0) {
					cnt++;
				}
			}

			for (int dir = 0; dir < 4; dir++) {
				int nx = tree.x + dx[dir];
				int ny = tree.y + dy[dir];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == 0) {
					spreadTree[nx][ny] += (map[tree.x][tree.y] / cnt);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] += spreadTree[i][j];
			}
		}
	}

	private static void treeGrow() {

		for (int i = 0; i < treeList.size(); i++) {
			Point tree = treeList.get(i);

			int cnt = 0;
			for (int dir = 0; dir < 4; dir++) {
				int nx = tree.x + dx[dir];
				int ny = tree.y + dy[dir];
				// 옆에 나무가 있는 경우
				if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != 0 && map[nx][ny] != -1) {
					cnt++;
				}
			}
			map[tree.x][tree.y] += cnt;
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
