package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14891 {

	public static int[][] wheel;
	public static int[] score = { 0, 1, 2, 4, 8 };

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
			rotate(idx, dir);
		}
	
		int sum = 0;

		for (int i = 1; i <= 4; i++) {
			if (wheel[i][1] == 1) {
				sum += score[i];
			}
		}
		System.out.println(sum);

	}

	private static void rotate(int idx, int dir) {
		Right(idx + 1, dir);
		Left(idx - 1, dir);
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
		int tmp = wheel[idx][8];
		for (int i = 8; i > 1; i--) {
			wheel[idx][i] = wheel[idx][i - 1];
		}
		wheel[idx][1] = tmp;
	}

	private static void Left(int idx, int dir) {
		if (idx < 1)
			return;
		else if (wheel[idx][3] == wheel[idx + 1][7])
			return;
		Left(idx - 1, -dir);
		if (dir == 1) {
			rotateLeft(idx);
		} else {
			rotateRight(idx);
		}
		
	}

	private static void Right(int idx, int dir) {
		if (idx > 4)
			return;
		else if (wheel[idx][7] == wheel[idx - 1][3])
			return;

		Right(idx + 1, -dir);
		if (dir == 1) {
			rotateLeft(idx);
		} else {
			rotateRight(idx);
		}
		
	}
}
