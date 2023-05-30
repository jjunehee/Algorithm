import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Solution2 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Pos> mList;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int maxCore;
	static int minLine;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			maxCore = Integer.MIN_VALUE;
			minLine = Integer.MAX_VALUE;
			map = new int[N][N];
			visited = new boolean[N][N];
			mList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
							mList.add(new Pos(i, j)); // 멕시노스 추가
						}
						visited[i][j] = true;
					}
				}
			}

			search(0, 0, 0);
			sb.append(minLine).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void search(int idx, int coreCnt, int lineCnt) {
		if (idx == mList.size()) {
			if (maxCore <= coreCnt) {

				if (maxCore < coreCnt) {
					maxCore = coreCnt;
					minLine = lineCnt;
				} else {
					minLine = Math.min(minLine, lineCnt);
				}
			}
			return;
		}

		Pos cur = mList.get(idx);

		int nx, ny;
		for (int dir = 0; dir < 4; dir++) {

			int cnt = 0;
			nx = cur.x;
			ny = cur.y;
			while (true) {
				nx = nx + dx[dir];
				ny = ny + dy[dir];
				if (boundCheck(nx, ny)) {
					fill(cur.x, cur.y, dir, true);
					search(idx + 1, coreCnt + 1, lineCnt + cnt);
					fill(cur.x, cur.y, dir, false);
					break;
				} else if (visited[nx][ny]) {
					break;
				}
				cnt++;
			}
		}
		search(idx + 1, coreCnt, lineCnt);

	}

	private static void fill(int x, int y, int dir, boolean bool) {

		int nx = x;
		int ny = y;
		while (true) {
			nx = nx + dx[dir];
			ny = ny + dy[dir];
			if (boundCheck(nx, ny)) {
				break;
			} else {
				visited[nx][ny] = bool;
			}
		}
	}

	public static boolean boundCheck(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return true;
		} else {
			return false;
		}
	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
