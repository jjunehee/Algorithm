package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14891 {

	public static int[][] wheel;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		wheel = new int[5][5];
		for (int i = 1; i < 5; i++) {
			String input = br.readLine();
			for (int j = 1; j < 5; j++) {
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

	}

	private static void operation(int idx, int dir) {
		rotate(idx, dir);
		right(idx + 1, dir);
		left(idx - 1, dir);
	}

	private static void rotate(int idx, int dir) {

	}

	private static void left(int idx, int dir) {
		if (idx < 1) {
			return;
		}
		
		
	}

	private static void right(int idx, int dir) {
		if (idx > 4) {
			return;
		}
		
		
	}
}
