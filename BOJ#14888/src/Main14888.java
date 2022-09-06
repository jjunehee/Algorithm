package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14888 {

	public static int[] numbers;
	public static int[] operator;
	public static int N;
	public static int MAX = Integer.MIN_VALUE;
	public static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		operator = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}

		dfs(numbers[0], 1);
		System.out.println(MAX);
		System.out.println(MIN);
	}

	private static void dfs(int num, int idx) {
		if (idx == N) {
			MAX = Math.max(MAX, num);
			MIN = Math.min(MIN, num);
		}

		for (int i = 0; i < 4; i++) {
			if (operator[i] >= 1) {
				operator[i]--;
				switch (i) {
				case 0:
					dfs(num + numbers[idx], idx + 1);
					break;
				case 1:
					dfs(num - numbers[idx], idx + 1);
					break;
				case 2:
					dfs(num * numbers[idx], idx + 1);
					break;
				case 3:
					dfs(num / numbers[idx], idx + 1);
					break;
				}
				operator[i]++;
			}
		}
	}
}