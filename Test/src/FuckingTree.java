import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		ban = new boolean[n][n];
		mInfo = new int[n][n];
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
		System.out.println(ret);
	}

	private static void simulation() {

		year = 1;
		while (year <= m) {

			
			// 나무 성장
			treeGrow();

			// 나무 번식
			treeSpread();

			// 체조제 처리
			medicine();

			// 삭제할 제초제 처리
			removeMedicine();

			year++;
		}

	}

	private static void treeGrow() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (map[i][j] > 0) {
					int cnt = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						// 옆에 나무가 있는 경우
						if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] > 0
								&& !ban[nx][ny]) {
							cnt++;
						}
					}
					map[i][j] += cnt;
				}
			}
		}
	}

	private static void treeSpread() {

		spreadTree = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (map[i][j] != 0 && map[i][j] != -1) {
					int cnt = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == 0 && !ban[nx][ny]) {
							cnt++;
						}
					}

					for (int dir = 0; dir < 4; dir++) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == 0 && !ban[nx][ny]) {
							spreadTree[nx][ny] += (map[i][j] / cnt);
						}
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] += spreadTree[i][j];
			}
		}
	}

	private static void removeMedicine() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (year == mInfo[i][j]) {
					ban[i][j] = false;
				}
			}
		}
	}

	private static void medicine() {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0) { // 나무가 있다면
					int sum = map[i][j]; // 그 나무에 대한 박멸갯수를 카운트 해본다.
					for (int dir = 0; dir < 4; dir++) { // 4개 방향
						int nowX = i;
						int nowY = j;
						for (int t = 0; t < k; t++) {
							int nx = nowX + mdx[dir];
							int ny = nowY + mdy[dir];
							if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] > 0) { // 대각선 방향에 나무가 있다면 sum 추가
								sum += map[nx][ny];
								
							} else {
								break;
							}
							nowX = nx;
							nowY = ny;
						}
					}
					if (sum > max) {
						max = sum;
						mx = i;
						my = j;
					}
				}
			}
		}

		ret += map[mx][my];
		map[mx][my] = 0;
		mInfo[mx][my] = year + c;
		ban[mx][my] = true;

		for (int dir = 0; dir < 4; dir++) {
			int nowX = mx;
			int nowY = my;

			for (int t = 0; t < k; t++) {
				int nx = nowX + mdx[dir];
				int ny = nowY + mdy[dir];

				if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] > 0) {
					ret += map[nx][ny];
					map[nx][ny] = 0;
					mInfo[nx][ny] = year + c;
					ban[nx][ny] = true;
				} else if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == 0) {
					if(ban[nx][ny]) {
						mInfo[nx][ny] = year + c;
						break;
					} else {
						mInfo[nx][ny] = year + c;
						ban[nx][ny] = true;
						break;
					}
				} else {
					break;
				}
				nowX = nx;
				nowY = ny;
			}
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
