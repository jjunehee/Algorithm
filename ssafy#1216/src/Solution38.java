package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution38 {
	static final int MapSize = 100;
	static char[][] map;
	static int max;
	static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		answer = new int[10+1];
		for (int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine());
			map = new char[MapSize][MapSize];
			for (int i = 0; i < MapSize; i++) {
				String str = br.readLine();
				for (int j = 0; j < MapSize; j++) {
					map[i][j] = str.charAt(j);
				}
			}

			max = Integer.MIN_VALUE;
			for (int L = 0; L <= map.length; L++) {

				// row check
				for (int i = 0; i < MapSize; i++) {
					for (int j = 0; j < MapSize - L + 1; j++) {
						boolean check = true;
						for (int k = 0; k < L / 2; k++) {
							if (map[i][j + k] != map[i][j + L - k - 1]) {
								check = false;
								break;
							}
						}
						if (check && max < L) {
							max = L;
						}
					}
				}

				// column check
				for (int j = 0; j < MapSize; j++) {
					for (int i = 0; i < MapSize - L + 1; i++) {
						boolean check = true;
						for (int k = 0; k < L / 2; k++) {
							if (map[i + k][j] != map[i + L - k - 1][j]) {
								check = false;
								break;
							}
						}
						if (check && max < L) {
							max = L;
						}
					}
				}
			}
			answer[t] = max;
		}

		for (int i = 1; i <= 10; i++) {
			System.out.println("#" + i + " " + answer[i]);
		}
	}
}