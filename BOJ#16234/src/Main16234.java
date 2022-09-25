package src;

import java.io.*;
import java.util.*;

public class Main16234 {

	static int[][] map;
	static boolean[][] visited;
	static int L, R, N;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static ArrayList<Country> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(solution());
	}

	private static int solution() {
		int time = 0;
		while (true) {
			boolean possible = false;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						int sum = BFS(i, j);
						if (list.size() > 1) {
							changePopulate(sum);
							possible = true;
						}
					}
				}
			}

			if (!possible) {
				return time;
			}
			time++;
		}

	}

	private static void changePopulate(int sum) {
		int average = sum / list.size();
		for (Country country : list) {
			map[country.x][country.y] = average;
		}
	}

	private static int BFS(int x, int y) {
		Queue<Country> q = new LinkedList<>();
		list = new ArrayList<>();

		q.offer(new Country(x, y));
		list.add(new Country(x, y));
		int sum = map[x][y];
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Country cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
					int value = Math.abs(map[nx][ny] - map[cur.x][cur.y]);
					if (value >= L && value <= R) {
						q.offer(new Country(nx, ny));
						list.add(new Country(nx, ny));
						sum += map[nx][ny];
						visited[nx][ny] = true;
					}

				}

			}
		}
		return sum;
	}

	public static class Country {
		int x, y;

		public Country(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
