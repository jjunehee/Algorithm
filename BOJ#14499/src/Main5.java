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

		// �ֻ��� ��ġ
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		Marble marble = new Marble(x, y);
		// ��� ����
		int K = Integer.parseInt(st.nextToken());
		order = new int[K];

		// map �Է�
		for (int i = 0; i < N; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st1.nextToken());
			}
		}

		// ��ġ�̵� ��� �Է�
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st2.nextToken());
		}

		// �ֻ��� �̵�
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

		// �ֻ��� �̵��ϸ鼭 Bottom �������ֱ�
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
