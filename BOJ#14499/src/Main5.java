package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main5 {

	public static int[][] map;
	public static int x, y;
	public static int[] order;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// map
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		// 주사위 위치
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		Marble marble = new Marble(x, y);
		// 명령 개수
		int K = Integer.parseInt(st.nextToken());
		order = new int[K];

		// map 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st1.nextToken());
			}
		}

		// 위치이동 명령 입력
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st2.nextToken());
		}

		// 주사위 이동
		for (int i = 0; i < K; i++) {
			marble.move(order[i]);
			System.out.println(marble.printTop());
		}

	}

	public static class Marble {
		private int x;
		private int y;

		public Marble(int x, int y) {
			this.x = x;
			this.y = y;
		}

		// 주사위 이동하면서 Bottom 변경해주기
		boolean move(int dir) {
			switch (dir) {
			case 1:
				
			case 2:
				
			case 3:
				
			case 4:
				
			}
			return true;
		}

		void copy() {

		}

		int printTop() {

			return -1;
		}
	}
}
