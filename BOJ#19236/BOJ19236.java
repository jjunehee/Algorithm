import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 청소년 상어
public class BOJ19236 {

	static int[][] map;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static Fish shark;
	static int result;
	static int maxResult = Integer.MIN_VALUE;
	static List<Fish> fishList = new ArrayList<>();
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[4][4];
		visited = new boolean[4][4];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				Fish fish = new Fish(i, j, num, dir-1, true);
				map[i][j] = fish.num;
				fishList.add(fish);
			}
		}

		Collections.sort(fishList);

		// 0,0
		shark = new Fish(0, 0, 0, fishList.get(map[0][0]).dir, true);
		fishList.add(shark);
		Collections.sort(fishList);
		
		result += map[0][0];
		map[0][0] = -1;

		simulation(0, 0, shark.dir);
		
		System.out.println(maxResult);
	}

	public static void simulation(int sharkX, int sharkY, int sharkDir) { // DFS를 이용한 시뮬레이션

		if(maxResult > result) {
			maxResult = result;
		}
		
		fishMove();

		// sharkMove
		for (int dist = 1; dist < 4; dist++) {
			int nx = sharkX + dx[sharkDir];
			int ny = sharkY + dy[sharkDir];

			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] == 0 || visited[nx][ny]) {
				continue;
			}

			result += map[nx][ny];
			shark.dir = fishList.get(map[nx][ny]).dir;
			fishList.get(map[nx][ny]).isAlive = false;
			map[sharkX][sharkY] = 0;
			int tmp = map[nx][ny];
			map[nx][ny] = -1;
			visited[nx][ny] = true;

			simulation(nx, ny, shark.dir);

			visited[nx][ny] = false;
			fishList.get(tmp).isAlive = true;
			shark.dir = sharkDir;
			result -= map[nx][ny];
			map[nx][ny] = tmp;
			map[sharkX][sharkY] = -1;

			sharkX = nx;
			sharkY = ny;
		}

	}

	public static void fishMove() {
		// 물고기 이동
		for (Fish fish : fishList) {

			if (!fish.isAlive) {
				continue;
			}

			int nx, ny;

			for (int i = 0; i < 8; i++) {
				nx = fish.x + dx[fish.dir];
				ny = fish.y + dy[fish.dir];

				// 맵 안에 있고, 상어가 없다면
				if (check(nx, ny) && map[nx][ny] != -1) {

					if (map[nx][ny] >= 1) { // 다른 물고기가 있다면
						int temp = map[nx][ny];
						map[nx][ny] = fish.num;
						map[fish.x][fish.y] = temp;

						fishList.get(map[nx][ny]).x = fish.x;
						fishList.get(map[nx][ny]).y = fish.y;

					} else { // 비어있는 공간이라면
						map[nx][ny] = fish.num;
						map[fish.x][fish.y] = 0;
					}

					fish.x = nx;
					fish.y = ny;
					
					break;
				}

				fish.dir = (fish.dir + 1) % 8;
			}

		}
	}

	public static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
			return false;
		}
		return true;
	}

	public static class Fish implements Comparable<Fish> {
		int x, y;
		int num;
		int dir;
		boolean isAlive;

		public Fish(int x, int y, int num, int dir, boolean isAlive) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			this.isAlive = true;
		}

		@Override
		public int compareTo(Fish o) {
			return this.num - o.num;
		}
	}
}
