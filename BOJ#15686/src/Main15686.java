package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main15686 {
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		Queue<House> house = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					house.add(new House(i, j));
				}
			}
		}
		solution(house);

	}

	private static void solution(Queue<House> house) {

		while(!house.isEmpty()) {
			House home = house.poll();
			int x = home.x;
			int y = home.y;
			
			chickenDistance(x,y);
		}
		
	}

	private static void chickenDistance(int x, int y) {
		if(map[x][y]==2) {
			return;
		}
		
		
	}

	public static class House {
		int x;
		int y;

		public House(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
