import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 청소년 상어
public class BOJ19236 {

	static Fish[][] map;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static Fish shark;
	static int result;
	static List<Fish> fishList = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new Fish[4][4];

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				Fish fish = new Fish(i, j, num, dir, true);
				map[i][j] = fish;
				fishList.add(fish);
			}
		}

		Collections.sort(fishList);

		for (Fish fish : fishList) {
			System.out.println(fish.num);
		}

		// 0,0
		shark = new Fish(0, 0, 0, map[0][0].dir, true);
		result += map[0][0].num;
		map[0][0] = shark;

		simulation(0, 0, shark.dir);
	}

	public static void simulation(int sharkX, int sharkY, int sharkDir) { // DFS를 이용한 시뮬레이션

		fishMove();

		// sharkMove
		for (int d = 1; d < 4; d++) {
			int nx = sharkX + dx[sharkDir];
			int ny = sharkY + dy[sharkDir];

			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] == null) {
				continue;
			}

			result += map[nx][ny].num;
			shark.dir = map[nx][ny].dir;
			map[nx][ny].isAlive = false;
			map[nx][ny] = shark;

			simulation(nx, ny, shark.dir);

			map[nx][ny].isAlive = true;
			shark.dir = sharkDir;
			result -= map[nx][ny].num;

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
				if (check(nx, ny) && map[nx][ny].num != 0) {

					if (map[nx][ny] != null) { // 다른 물고기가 있다면
						Fish temp = map[nx][ny];
						map[nx][ny] = fish;
						map[fish.x][fish.y] = temp;
						
						
					} else { // 비어있는 공간이라면
						map[nx][ny] = fish;
					}

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
