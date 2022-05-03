package src;

import java.io.*;
import java.util.*;

//아기 상어 문제
public class Main6 {

	public static int[][] map;
	public static Shark shark;
	public static int time = 0;
	public static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		// map 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 0)
					continue;

				if (map[i][j] == 9) {
					shark = new Shark(i, j, 2);
				}
			}
		}

		while (shark.canEatFish()) {

		}
		System.out.println(time);

	}

	public static class Shark {

		private int[] dx = { -1, 0, 1, 0 };
		private int[] dy = { 0, 1, 0, -1 };
		int i;
		int j;
		int size;
		private static Target target;

		public Shark(int i, int j, int size) {
			this.i = i;
			this.j = j;
			this.size = size;
		}

		private boolean canEatFish() {

			boolean isTarget = true;
			isTarget = search(this.i,this.j);

			while (isTarget) {
				moveAndEat();
				isTarget = search(this.i,this.j);
			}

			return false;
		}

		private void moveAndEat() {
			
			int count = Math.abs(target.i - this.i) + Math.abs(j - this.j) 
			time += count;
			
			this.i = target.i;
			this.j = target.j;
			map[i][j] = 0;
			
			return;
		}

		private boolean search(int curi,int curj) {
			
			// bfs
			while (this.i < N || this.j < N) {

				for (int dir = 0; dir < 4; dir++) {
					
					int nx = curi + dx[dir];
					int ny = curj + dy[dir];
					
					if (this.size > map[nx][ny]) {
						target = new Target(nx, ny);
						return true;
					} else {
						search(nx,ny);
					}
				}

			}
			return false;

		}

		private static class Target {
			int i;
			int j;

			public Target(int i, int j) {
				this.i = i;
				this.j = j;
			}
		}
	}

}
