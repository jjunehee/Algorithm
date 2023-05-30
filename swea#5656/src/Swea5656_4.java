import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Swea5656_4 {
	static int[][] map;
	static int[][] copyMap;
	static int[] pick;
	static int N, W, H;
	static int ret;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // N개의 구슬을 뽑는다.
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ret = Integer.MAX_VALUE;
			pick = new int[N];
			permu(0); // 중복순열로 구슬 N개 고르기
			sb.append(ret).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void permu(int cnt) {
		
		if(ret ==0) {
			return;
		}
		
		if (cnt == N) {
			int result = gameStart();
			ret = Math.min(ret, result);
			return;
		}

		for (int i = 0; i < W; i++) {
			pick[cnt] = i;
			permu(cnt + 1);
		}
	}

	public static int gameStart() {

		Queue<Pos> q = new LinkedList<>();

		copyMap = new int[H][W];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			int cur = pick[i];

			int target = 0;
			while (true) {

				if (copyMap[target][cur] > 0) {
					q.add(new Pos(target, cur, copyMap[target][cur]));
					break;
				}
				target++;
				if (target == H) {
					break;
				}
			}

			while (!q.isEmpty()) {

				Pos boom = q.poll();
				int power = boom.power;
				copyMap[boom.x][boom.y] = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nx = boom.x;
					int ny = boom.y;
					for (int p = 1; p < power; p++) {
						nx = nx + dx[dir];
						ny = ny + dy[dir];
						if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
							if (copyMap[nx][ny] >= 2) {
								q.add(new Pos(nx, ny, copyMap[nx][ny]));
							}
							copyMap[nx][ny] = 0;
						}
					}
				}
			}

			moveDown();

		}

		return getRemain();

	}

	public static int getRemain() {
		int cnt = 0;
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (copyMap[j][i] > 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static void moveDown() {

		Stack<Integer> s = new Stack<>();

		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (copyMap[j][i] > 0) {
					s.add(copyMap[j][i]);
					copyMap[j][i] = 0;
				}
			}

			int r = H - 1;
			while (!s.isEmpty()) {
				copyMap[r--][i] = s.pop();
			}
		}
	}

	public static class Pos {
		int x, y, power;

		public Pos(int x, int y, int power) {
			this.x = x;
			this.y = y;
			this.power = power;
		}
	}
}
