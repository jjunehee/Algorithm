package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14891 {

	public static int[][] wheel;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		wheel = new int[5][9];
		for (int i = 1; i <= 4; i++) {
			String input = br.readLine();
			for (int j = 1; j <= 8; j++) {
				wheel[i][j] = input.charAt(j - 1) - '0';
			}
		}

		int K = Integer.parseInt(br.readLine());

		for (int i = 1; i <= K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			operation(idx, dir);
		}
		
		for()
			
			
			
			
//		for (int[] i : wheel) {
//			for (int j : i) {
//				System.out.print(j);
//			}
//			System.out.println();
//		}

	}

	private static void operation(int idx, int dir) {
		rotate(idx, dir);
		rightwheel(idx + 1, dir);
		leftwheel(idx - 1, dir);
	}

	private static void rotate(int idx, int dir) {
		if (dir == 1) { // 시계방향 회전

			rotateRight(idx);

		} else { // 반시계방향 회전

			rotateLeft(idx);

		}
	}

	private static void rotateLeft(int idx) {
		int tmp = wheel[idx][1];
		for (int i = 1; i < 8; i++) {
			wheel[idx][i] = wheel[idx][i + 1];
		}
		wheel[idx][8] = tmp;
	}

	private static void rotateRight(int idx) {
		int tmp = wheel[idx][7];
		for (int i = 8; i > 1; i--) {
			wheel[idx][i] = wheel[idx][i - 1];
		}
		wheel[idx][1] = tmp;
	}

	private static void leftwheel(int idx, int dir) {
		if (idx < 0)
			return;
		else if (wheel[idx][3] == wheel[idx + 1][7])
			return;

		if (dir == 1) {
			rotateLeft(idx);
		} else {
			rotateRight(idx);
		}

	}

	private static void rightwheel(int idx, int dir) {
		if (idx > 4)
			return;
		else if (wheel[idx][7] == wheel[idx - 1][3])
			return;

		if (dir == 1) {
			rotateLeft(idx);
		} else {
			rotateRight(idx);
		}

	}
}
