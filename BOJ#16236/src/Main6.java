package src;

import java.io.*;
import java.util.*;

//아기 상어 문제
public class Main6 {

	public static int[][] map;
	public static Fish shark, fish;
	public static int time = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		// map 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 0)
					continue;

				if (map[i][j] == 9) {
					shark = new Fish(i, j, 2);
				} else {
					fish = new Fish(i, j, map[i][j]);
				}
			}

		}

		while (shark.canEatFish()) {

		}
		System.out.println(time);

	}

	public static class Fish {

		private int[] dx = { -1, 0, 1, 0 };
		private int[] dy = { 0, 1, 0, -1 };

		public Fish(int i, int j, int size) {

		}

		private boolean canEatFish() {

			while (search()) {
				move();
				eat();
			}

			return false;
		}

		private void move() {
			while (reach()) {
				time++;
			}
		}

		private boolean reach() {
			return false;
		}

		private void eat() {

		}

		private boolean search() {

			return true;
		}
	}

}
