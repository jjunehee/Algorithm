package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution33 {
	static int[][] map;
	static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new int[10];
		for (int t = 0; t < 10; t++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[8][8];
			for (int i = 0; i < 8; i++) {
				String st = br.readLine();
				for (int j = 0; j < 8; j++) {
					map[i][j] = st.charAt(j);
				}
			}

			// row check
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8 - N + 1; j++) {
					int flag = 0;
					for (int p = 0; p < N / 2; p++) {
						if (map[i][j + p] != map[i][j + N - 1 - p]) {
							flag = 1;
						}
					}
					if (flag == 0) {
						answer[t]++;
					}
				}
			}

			// column check
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8 - N + 1; i++) {
					int flag = 0;
					for (int p = 0; p < N / 2; p++) {
						if (map[i + p][j] != map[i + N - 1 - p][j]) {
							flag = 1;
						}
					}
					if (flag == 0) {
						answer[t]++;
					}
				}
			}

		}

		for (int i = 0; i < 10; i++) {
			System.out.println("#" + (i+1) + " " + answer[i]);
		}
	}
}
