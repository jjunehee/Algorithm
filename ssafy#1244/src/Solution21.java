package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution21 {
	static int[] answer;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		answer = new int[n];

		for (int i = 0; i < n; i++) {
			max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			int count = Integer.parseInt(st.nextToken());

			dfs(str, 0, count);
			answer[i] = max;
		}
		for (int i = 0; i < n; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}

	private static void dfs(String str, int depth, int count) {
		if (depth == count) {
			max = Math.max(max, Integer.parseInt(str));
		}
		char[] num = str.toCharArray();
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (num[i] < num[j]) {
					char temp = num[i];
					num[i] = num[j];
					num[j] = temp;
					dfs(String.valueOf(num), depth + 1, count);
				}
			}
		}
	}
}
