package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16236 {
	static int[][] map;
	static ArrayList<Fish> fishList = new ArrayList<>();
	static Fish shark;
	static boolean HaveEat = true;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int N;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					if (map[i][j] == 9) {
						shark = new Fish(i, j, 2, 0);
						break;
					}
				}
			}
		}

		simulation(shark);
	}

	private static void simulation(Fish shark) {
		int time = 0;
		int eat = 0;
		int age = 0;
		Queue<Fish> q = new LinkedList<>();
		q.add(shark);

		while (true) {
			while (!q.isEmpty()) {
				Fish fish = q.poll();
				int x = fish.x;
				int y = fish.y;
				int size = fish.size;
				int dist = fish.dist;

				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || nx >= N || ny < 1 || ny >= N || visited[nx][ny]) {
						continue;
					}
					
					if (map[nx][ny] != 0 && map[nx][ny] <= size) { // ���հ� �߰�
						q.add(new Fish(nx, ny, map[nx][ny], dist + 1));
						fishList.add(new Fish(nx, ny, map[nx][ny], dist + 1));
						System.out.println("tt");
					} else { // ���� �߰� ���ϰ� �̵���
						q.add(new Fish(nx, ny, map[nx][ny], dist + 1));
						
					}

				}
			}
			
			if (fishList.isEmpty()) {
				System.out.println(time);
				return;
			}

			Fish selectedFish = fishList.get(0);
			for (int i = 1; i < fishList.size(); i++) {
				Fish fish = fishList.get(i);
				if (selectedFish.dist > fish.dist) {
					selectedFish = fish;
				} else if (selectedFish.dist == fish.dist) {
					if (selectedFish.x > fish.x) {
						selectedFish = fish;
					} else if (selectedFish.x == fish.x) {
						if (selectedFish.y > fish.y) {
							selectedFish = fish;
						}
					}
				}
			}

			time += selectedFish.dist;
			eat++;
			map[selectedFish.x][selectedFish.y] = 0;
			if (eat == age) {
				age++;
				eat = 0;
			}
			q.add(selectedFish);
		}

	}

	public static class Fish {
		int x;
		int y;
		int dist;
		int size;

		public Fish(int x, int y, int size, int dist) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.dist = dist;
		}

	}
}
