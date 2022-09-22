package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main15686 {
	public static int[][] map;
	public static int Min = Integer.MAX_VALUE;
	public static int N, M;
	static ArrayList<Place> chicken = new ArrayList<>();
	static ArrayList<Place> house = new ArrayList<>();
	static ArrayList<Place> chickenList = new ArrayList<>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					house.add(new Place(i, j));
				} else if (map[i][j] == 2) {
					chicken.add(new Place(i, j));
				}
			}
		}
		visited = new boolean[chicken.size()];
		DFS(0, 0);
		System.out.println(Min);

	}

	private static void DFS(int idx, int count) {
		if (count == M) {
			int total = 0;
			for (int i = 0; i < house.size(); i++) {

				int ret = Integer.MAX_VALUE;
				for (int j = 0; j < chickenList.size(); j++) {

					int dist = Math.abs(house.get(i).x - chickenList.get(j).x)
							+ Math.abs(house.get(i).y - chickenList.get(j).y);

					ret = Math.min(ret, dist);

				}
				total += ret;

			}
			Min = Math.min(Min, total);

		}
		for (int i = idx; i < chicken.size(); i++) {
			chickenList.add(chicken.get(i));
			DFS(idx + 1, count + 1);
			chickenList.remove(chickenList.size() - 1);

		}
	}

	public static class Place {
		int x;
		int y;

		public Place(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
