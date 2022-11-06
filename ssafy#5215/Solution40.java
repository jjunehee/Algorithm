import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution40 {
	static int N, L;
	static int[] score;
	static int[] calorie;
	static int max;
	static int[] answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int t = 0; t < T; t++) {
			max = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			score = new int[N];
			calorie = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				calorie[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0, 0, 0);

			answer[t] = max;
		}

		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i+1) + " " + answer[i]);
		}
	}

	public static void dfs(int idx, int totalScore, int totalCalorie) {
		if (totalCalorie > L) {
			return;
		}
		if (idx == N) {
			max = Math.max(max, totalScore);
			return;
		}

		dfs(idx + 1, totalScore + score[idx], totalCalorie + calorie[idx]);
		dfs(idx + 1, totalScore, totalCalorie);

	}
}