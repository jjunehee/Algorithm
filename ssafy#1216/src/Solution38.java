package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution38 {
	static char[][] map;
	static final int mapSize = 100;
	static int max;
	static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new int[10];
		for (int t = 0; t < 10; t++) {
			int n = Integer.parseInt(br.readLine());
			map = new char[mapSize][mapSize];
			for (int i = 0; i < 100; i++) {
				String str = br.readLine();
				for (int j = 0; j < 100; j++) {
					map[i][j] = str.charAt(j);
				}
			}

			max = Integer.MIN_VALUE;
			for (int L = 0; L <= mapSize; L++) {
				// row check
				for (int i = 0; i < mapSize; i++) {
					for (int j = 0; j < mapSize - L + 1; j++) {
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

				// column
				for (int j = 0; j < mapSize; j++) {
					for (int i = 0; i < mapSize - L + 1; i++) {
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
		for (int i = 0; i < 10; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}
}
