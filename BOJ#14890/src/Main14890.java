package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14890 {
	public static int N;
	public static int L;
	public static int[][] map;
	public static int count;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		count = 0;
		for (int i = 0; i < N; i++) {

			if (search(i, 0, 0)) {
				count++;// 행
			}
			if (search(0, i, 1)) {
				count++;// 열
			}
		}
		System.out.println(count);
	}

	private static boolean search(int x, int y, int flag) {
		int[] height = new int[N];
		boolean[] installed = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (flag == 0) {
				height[i] = map[x][i];
			} else {
				height[i] = map[i][y];
			}
		}

		for (int i = 0; i < N - 1; i++) {

			if (height[i] == height[i + 1]) {
				continue;
			} else if (height[i] + 1 == height[i + 1]) { // 오르막

				for (int j = i; j > i - L; j--) {

					if (j < 0) {
						return false;
					}
					if (installed[j]) {
						return false;
					}
					if (height[i] != height[j]) {
						return false;
					}
					installed[j] = true;

				}

			} else if (height[i] - 1 == height[i + 1]) { // 내리막

				for (int j = i + 1; j < i + 1 + L; j++) {
					if (j > N - 1) {
						return false;
					}
					if (installed[j]) {
						return false;
					}
					if (height[i + 1] != height[j]) {
						return false;
					}
					installed[j] = true;
				}
			} else {
				return false;
			}
		}
		return true;
	}
}
