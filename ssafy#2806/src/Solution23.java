package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution23 {
	static int[] answer;
	static int count;
	static int[] map;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			map = new int[N];
			count = 0;
			dfs(0);
			answer[i] = count;
		}

		for (int i = 1; i <= T; i++) {
			System.out.println("#" + i + " " + answer[i]);
		}

	}

	public static void dfs(int depth) {
		if (depth == N) {
			count++;
			return;
		}

		for (int i = 0; i < N; i++) {
			map[depth] = i;

			if (possible(depth)) {
				dfs(depth + 1);
			}
		}
	}

	public static boolean possible(int depth) {

		for (int i = 0; i < depth; i++) { // 행 검사
			if (map[depth] == map[i]) {
				return false;
			}

			if (Math.abs(depth - i) == Math.abs(map[depth] - map[i])) { // 대각선 검사
				return false;
			}
		}

		return true;
	}
}