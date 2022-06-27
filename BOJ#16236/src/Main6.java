package src;

import java.io.*;
import java.util.*;

//아기 상어 문제
public class Main6 {

	public static int[][] map;
	public static Shark shark;
	public static Fish fish;
	public static ArrayList<Fish> fishList = new ArrayList<Fish>();
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
				else if (map[i][j] == 9) {
					shark = new Shark(i, j, 2);
					map[i][j] = 0;
				} else {
					fish = new Fish(i,j,map[i][j]);
					fishList.add(fish);
				}
			}
		}
		
		Collections.sort(fishList);
			

		
		for(Fish s : fishList) {
			System.out.print(s.size);
		}

		while (shark.canEatFish()) {

		}
		System.out.println(time);

	}

	public static class Shark  {

		private int[] dx = { -1, 0, 1, 0 };
		private int[] dy = { 0, 1, 0, -1 };
		int i;
		int j;
		int size;

		public Shark(int i, int j, int size) {
			this.i = i;
			this.j = j;
			this.size = size;
		}

		public boolean canEatFish() {
			//탐색
			
			return false;
		}

	}
	
	public static class Fish implements Comparable<Fish> {
		int x;
		int y;
		int size;
		public Fish(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
		
		@Override
		public int compareTo(Fish arg) {
			return this.size-arg.size;
		}
	}


}
