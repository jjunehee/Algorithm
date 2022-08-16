package src;

import java.io.*;
import java.util.*;

public class Main14888 {

	static int[] numbers;
	static int[] operators;
	static int N;
	public static int Max = Integer.MIN_VALUE;
	public static int Min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}

		dfs(numbers[0], 1);
		System.out.println(Max);
		System.out.println(Min);
	}

	private static void dfs(int num, int idx) {

		if (idx == N) {
			Max = Math.max(Max, num);
			Min = Math.min(Min, num);
		}

		for (int i = 0; i < 4; i++) {
			if (operators[i] >= 1) {
				operators[i]--;

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
				operators[i]++;
			}
		}
	}

}
