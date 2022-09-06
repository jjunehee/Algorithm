package src;

import java.io.*;
import java.util.*;

public class Main14889 {
	public static int N;
	public static int[][] S;
	public static int Min = Integer.MAX_VALUE;
	public static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		S = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(1, 0);
		System.out.println(Min);
	}

	private static void dfs(int idx, int count) {
		if (count == N / 2) {
			calculate();
			return;
		}

		for (int i = idx; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i + 1, count + 1);
				visited[i] = false;
			}
		}

	}

	private static void calculate() {
		int team_link = 0;
		int team_start = 0;

		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (visited[i] == true && visited[j] == true) {
					team_start += S[i][j] + S[j][i];
				}
				else if (visited[i] == false && visited[j] == false) {
					team_link += S[i][j] + S[j][i];
				}
			}
		}

		int gap = Math.abs(team_start - team_link);
		if (gap == 0) {
			System.out.println(gap);
			System.exit(0);
		}
		Min = Math.min(Min, gap);

	}

}
