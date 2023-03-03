import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Swea5656_1 {

	static int[][] map;
	static int[][] copyMap;
	static int[] pick;
	static int N, W, H;
	static int min = Integer.MAX_VALUE;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		// 구슬을 떨어뜨린다.
		// 떨어뜨리면 맞는 벽돌이 있을 것이다. (그 열의 가장 위 벽돌을 찾아야함)
		// 그 벽돌 주변 벽돌도 범위에따라 터진다.
		// 주변 터진 벽돌을 큐에 넣고 그 터진 벽돌들도 똑같이 주변 벽돌들을 깨뜨린다.
		// 벽돌들 정리.

		// 이것을 구슬을 던지는 횟수 N만큼

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			pick = new int[N];
			// 일단 N개의 구슬을 뽑아줘야한다. (중복 순열)
			min = Integer.MAX_VALUE;
			permu(0);
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void permu(int cnt) {
		
		if(min==0) {
			return;
		}
		
		if (cnt == N) {
			// pick -> 구슬을 중복순열로 뽑았다.
			// 이 시점에 각 조합별로 맵을 따로 써줘야하므로 copyMap을 사용해야함
			copyMap();
			gameStart();
			int ret = getRemain();
			
			min = Math.min(min, ret);
			return;
		}

		for (int i = 0; i < W; i++) {
			pick[cnt] = i;
			permu(cnt + 1);
		}

	}

	private static int getRemain() {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (copyMap[i][j] > 0) {
					count++;
				}
			}
		}
		return count;
	}

	private static void copyMap() {

		copyMap = new int[H][W];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}

	private static void gameStart() {
		// pick에는 구슬을 담고 있음

		Queue<Integer> ball = new ArrayDeque<>(); // q에 일단 구슬들을 담자. (차례로 구슬을 떨어뜨리기 위해서)

		for (int i = 0; i < pick.length; i++) {
			ball.add(pick[i]);
		}

		int curBall;
		while (!ball.isEmpty()) { // 구슬을 모두 떨어뜨릴때까지!
			curBall = ball.poll();

			int r = 0;
			while (r < H) {

				if (copyMap[r][curBall] == 0) {
					r++;
					continue;
				} else {
					break;
				}
			}

			if (r == H) { // 그 열에 벽돌이 존재하지 않은 경우
				break; // 나가~!
			}
			// 여기까지 살아남았다면 r은 벽돌이 있는 위치!
			boom(r, curBall);
			// 다 터뜨리고 난 다음 정리
			moveDown();
			
		}

	}

	static Stack<Integer> stack = new Stack<>();

	private static void moveDown() {
		for (int j = 0; j < W; j++) {
			for (int i = 0; i < H; i++) {
				if (copyMap[i][j] > 0) {
					stack.add(copyMap[i][j]);
					copyMap[i][j] = 0;
				}
			}

			int r = H - 1;
			while (!stack.isEmpty()) {
				copyMap[r--][j] = stack.pop();
			}
		}
	}

	private static void boom(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		if (copyMap[x][y] > 1) {
			q.offer(new Point(x, y, copyMap[x][y]));
		}

		copyMap[x][y] = 0;

		Point cur;
		while (!q.isEmpty()) {
			cur = q.poll();
			for (int dir = 0; dir < 4; dir++) {

				int nx = cur.x;
				int ny = cur.y;
				for (int k = 1; k <= cur.cnt - 1; k++) {
					nx += dx[dir];
					ny += dy[dir];
					
					if (nx < 0 || nx >= H || ny < 0 || ny >= W || copyMap[nx][ny] == 0) {
						continue;
					}
					if (copyMap[nx][ny] > 1) {
						q.offer(new Point(nx, ny, copyMap[nx][ny]));
					}

					copyMap[nx][ny] = 0;
				}
			}
		}

	}

	public static class Point {
		int x;
		int y;
		int cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
