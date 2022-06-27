package src;

import java.io.*;
import java.util.StringTokenizer;

public class Main14500 {

	public static int N;
	public static int M;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int max = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st1.nextToken());
		M = Integer.parseInt(st1.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				dfs(i, j, 0, 0);
				exception(i, j);

			}
		}
		System.out.println(max);
	}

	private static void exception(int i, int j) {
		int wing = 4;
		int min = Integer.MAX_VALUE;
		int sum = map[i][j];
		for (int dir = 0; dir < 4; dir++) {
			int nextX = i + dx[dir];
			int nextY = j + dy[dir];
			
			if(wing==2) {
				return;
			}
			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
				wing--;
				continue;
			}
			
			min = Math.min(min,map[nextX][nextY]);
			sum = sum + map[nextX][nextY];
		}
		if(wing==4) {
			sum = sum - min;
		}
		max = Math.max(max, sum);
	

	}

	private static void dfs(int i, int j, int depth, int sum) {

		if (depth == 4) {
			max = Math.max(max, sum);
			return;
		}
		for (int dir = 0; dir < 4; dir++) {
			int nextX = i + dx[dir];
			int nextY = j + dy[dir];

			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || visited[nextX][nextY] == true) {
				continue;
			}

			visited[nextX][nextY] = true;
			dfs(nextX, nextY, depth + 1, sum + map[nextX][nextY]);
			visited[nextX][nextY] = false;
		}

	}

}
