package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution37 {
	static int[] answer;
	static int[][] map;
	static boolean[] visited;
	static int N,M;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int t = 0; t < T; t++) {
			max = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N + 1][N + 1];
			visited = new boolean[N + 1];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = map[y][x] = 1;
			}

			for (int i = 1; i <= N; i++) {
				dfs(1, i);
				visited[i] = false;
			}
			answer[t] = max;
		}
		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i+1) + " " + answer[i]);
		}
	}

	public static void dfs(int cnt, int v) {
		visited[v] = true;

		for (int i = 1; i <= N; i++) {
			if(map[i][v] == 1 && !visited[i]) {
				dfs(cnt+1,i);
				visited[i] = false;
			}
		}
		max = Math.max(max,cnt);
	}
}