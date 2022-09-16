package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14891 {

	public static int[][] wheel;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		wheel = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String input = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = input.charAt(j) - '0';
			}
		}

		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			operation(idx, dir);
		}
		for (int[] i : wheel) {
			for (int j : i) {
				System.out.print(j);
			}
			System.out.println();
		}

	}

	private static void operation(int idx, int dir) {
		rotate(idx, dir);
		right(idx + 1, dir);
		left(idx - 1, dir);
	}

	private static void rotate(int idx, int dir) {
		if (dir == 1) { // 시계방향 회전
			int tmp = wheel[idx][7];
			for (int i = 7; i > 0; i--) {
				wheel[idx][i] = wheel[idx][i - 1];
			}
			wheel[idx][0] = tmp;
		} else { // 반시계방향 회전
			int tmp = wheel[idx][0];
			for (int i = 0; i < 7; i++) {
				wheel[idx][i] = wheel[idx][i+1];
			}
			wheel[idx][7] = tmp;
		}
	}

	private static void left(int idx, int dir) {
		if (idx < 0) {
			return;
		}

	}

	private static void right(int idx, int dir) {
		if (idx > 4) {
			return;
		}

	}
}
