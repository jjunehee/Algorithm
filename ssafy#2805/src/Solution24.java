package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution24 {
	static int[][] map;
	static char[] temp;
	static int N;
	static int sum;
	static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int c = 0; c < N; c++) {
				st = new StringTokenizer(br.readLine());
				String str = st.nextToken();
				for (int r = 0; r < N; r++) {
					map[c][r] = str.charAt(r) - '0';
				}
			}
			sum = 0;
			calculate(map);
			answer[i] = sum;
		}
		for (int i = 1; i <= T; i++) {
			System.out.println("#" + i + " " + answer[i]);
		}
	}

	public static void calculate(int[][] map) {
		int mid = N / 2;
		int start = N / 2;
		int end = N / 2;

		for (int i = 0; i < N; i++) {
			for (int j = start; j <= end; j++) {
				sum += map[i][j];
			}

			if (i < mid) {
				start--;
				end++;
			} else {
				start++;
				end--;
			}
		}
	}
}
