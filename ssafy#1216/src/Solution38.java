
package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution38 {
	static char[][] map;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= 10; t++) {
			sb.append("#" + t + " ");
			int n = Integer.parseInt(br.readLine());
			map = new char[101][101];
			for (int i = 0; i < 100; i++) {
				char[] str = br.readLine().toCharArray();
				for (int j = 0; j < 100; j++) {
					map[i][j] = str[j];
				}
			}
			int max = 1;
			flag = true;
			for (int L = 1; L <= 100; L++) {
				// row check
				loopOut:
				for (int i = 0; i < 100; i++) {
					for (int j = 0; j < 100 - L + 1; j++) {
						flag = true;
						for (int p = 0; p < L / 2; p++) {
							if (map[i][j + p] != map[i][j + L - 1 - p]) {
								flag = false;
								break;
							}
						}
						if (flag && max < L) {
							max = L;
							break loopOut;
						}
					}
				}
				

				// column check;

				loopOut:
				for (int j = 0; j < 100; j++) {
					for (int i = 0; i < 100 - L + 1; i++) {
						flag = true;
						for (int p = 0; p < L / 2; p++) {
							if (map[i + p][j] != map[i + L - 1 - p][j]) {
								flag = false;
								break;
							}
						}
						if (flag && max < L) {
							max = L;
							break loopOut;
						}
					}
				}
			}
			sb.append(max).append("\n");

		}
		System.out.println(sb);

	}
}