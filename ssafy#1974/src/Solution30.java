package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution30 {
	static int[][] map;
	static int[] answer;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int t = 0; t < T; t++) {
			map = new int[10][10];
			for (int i = 1; i <= 9; i++) { // input
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 9; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int flag = 1;
			// row check
			for (int i = 1; i <= 9; i++) {

				if (flag == 1) {
					sum = 0;
					for (int j = 1; j <= 9; j++) {
						sum += map[i][j];
					}
					if (sum != 45) {
						flag = 0;
						break;
					}
				} else {
					break;
				}
			}

			// column check
			for (int j = 1; j <= 9; j++) {
				if (flag == 1) {
					sum = 0;
					for (int i = 1; i <= 9; i++) {
						sum += map[i][j];
					}
					if (sum != 45) {
						flag = 0;
						break;
					}
				} else {
					break;
				}
			}
			for (int i = 1; i <= 7; i += 3) {
				if (flag == 1) {
					for (int j = 1; j <= 7; j += 3) {
						sum = 0;
						if (check(i, j)) {
							flag = 1;
						} else {
							flag = 0;
							break;
						}

					}
				} else {
					break;
				}
			}

			answer[t] = flag;

		}
		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}

	public static boolean check(int i, int j) {
		for (int I = i; I < i + 3; I++) {
			for (int J = j; J < j + 3; J++) {
				sum += map[I][J];
			}	
		}
		if (sum != 45) {
			return false;
		}
		return true;
	}
}
