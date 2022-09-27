package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main16236 {
	static int[][] map;
	static ArrayList<Fish> fishList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					if (map[i][j] == 9) {
						Fish shark = new Fish(i, j, map[i][j]);
						break;
					}
					fishList.add(new Fish(i,j,map[i][j]));
				}
			}
		}

		Collections.sort(fishList,(t1,t2)->(t1.size-t2.size));
		
		solution();
	}

	private static void solution() {
		
		while(Fish.canEat()) {
			
		}
		
	}

	public static class Fish {
		int x;
		int y;
		int size;

		public Fish(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}

		public static boolean canEat() {
			
			return false;
		}
	}
}
