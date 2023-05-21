import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static int[][] map;
	static ArrayList<Point> mList;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int N;
	static boolean[][] visited;
	static int lineCnt;
	static int coreCnt;
	static int maxCore;
	static int minLine;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			mList = new ArrayList<>();
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
							mList.add(new Point(i, j)); // 멕시노스 추가
						}
						visited[i][j] = true;
					}
				}
			}

			lineCnt = 0;
			coreCnt = 0;
			maxCore = Integer.MIN_VALUE;
			minLine = Integer.MAX_VALUE;
			// 코어들을 싹 연결해주자
			dfs(0, 0, 0);
			sb.append(minLine).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int idx, int coreCnt, int lineCnt) {
		if (idx == mList.size()) { // 모든 코어들을 전부다 확인해봄
			if (maxCore <= coreCnt) {
				if (maxCore == coreCnt) {
					if (minLine > lineCnt) {
						maxCore = coreCnt;
						minLine = lineCnt;
					}
				} else {
					maxCore = coreCnt;
					minLine = lineCnt;
				}
			}
			return;
		}

		Point now = mList.get(idx); // 현재 확인하고자하는 코어

		for (int dir = 0; dir < 4; dir++) {
			int cnt = 0;
			int nx = now.x;
			int ny = now.y;
			while (true) {
				// 인접 위치
				nx = nx + dx[dir];
				ny = ny + dy[dir];
				if (boundCheck(nx, ny)) { // 전원 연결 성공
					fill(now.x, now.y, dir, cnt, true);
					dfs(idx + 1, coreCnt + 1, lineCnt + cnt);
					fill(now.x, now.y, dir, cnt, false);
					break;
				} else if (visited[nx][ny]) {
					break;
				}
				cnt++;
			}
		}
		dfs(idx + 1, coreCnt, lineCnt);

	}

	public static void fill(int x, int y, int dir, int cnt, boolean bool) {
		for (int i = 0; i < cnt; i++) {
			x += dx[dir];
			y += dy[dir];
			visited[x][y] = bool;
		}
	}

	private static boolean boundCheck(int nx, int ny) {

		if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
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