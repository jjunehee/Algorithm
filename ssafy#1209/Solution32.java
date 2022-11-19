

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution32 {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= 10; t++) {
			int test_case = Integer.parseInt(br.readLine());
			sb.append("#" + test_case + " ");
			map = new int[100][100];

			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = Integer.MIN_VALUE;
			// row check
			for (int i = 0; i < 100; i++) {
				int sum = 0;
				for (int j = 0; j < 100; j++) {
					sum += map[i][j];
				}
				max = Math.max(max, sum);
			}

			// columnn check
			for (int j = 0; j < 100; j++) {
				int sum = 0;
				for (int i = 0; i < 100; i++) {
					sum += map[i][j];
				}
				max = Math.max(max, sum);
			}

			// ´ë°¢¼± check
			int sumA = 0;
			int sumB = 0;
			for (int i = 0; i < 100; i++) {
				sumA += map[i][i];
				sumB += map[i][99 - i];
			}
			max = Math.max(max, Math.max(sumA, sumB));
			sb.append(max).append("\n");
		}
		System.out.println(sb);

	}
}