package src;

import java.io.*;
import java.util.*;

public class Main14502 {

	static final int dx[] = { 0, 0, 1, -1 }; // 상하좌우 방향 설정
	static final int dy[] = { 1, -1, 0, 0 }; // 상화좌우 방향 설정
	static int originalMap[][];
	static int n, m;
	static int maxSafeZone = Integer.MIN_VALUE; // 최대값을 찾기 위한 최소값 설정
	static int wallCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		originalMap = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				originalMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);

		System.out.println(maxSafeZone);
	}

	private static void dfs(int wallCnt) {

		if (wallCnt == 3) {
			bfs();
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (originalMap[i][j] == 0) {
					originalMap[i][j] = 1;
					dfs(wallCnt + 1);
					originalMap[i][j] = 0;
				}
			}
		}

	}

	private static void bfs() {
		int[][] copyMap = new int[n][m];

		for (int i = 0; i < n; i++) {
			copyMap[i] = originalMap[i].clone();
		}

		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (copyMap[i][j] == 2) {
					q.add(new Node(i, j));
				}
			}
		}

		while (!q.isEmpty()) {

			Node now = q.poll();
			int x = now.x;
			int y = now.y;

			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];

				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					if (copyMap[nx][ny] == 0) {
						copyMap[nx][ny] = 2;
						q.add(new Node(nx, ny));
					}

				}

			}

		}

		check(copyMap);

	}

	private static void check(int[][] copyMap) {
		
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (copyMap[i][j] == 0) {
					count++;
				}
			}
		}
		maxSafeZone = Math.max(maxSafeZone, count);
	}

	public static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
