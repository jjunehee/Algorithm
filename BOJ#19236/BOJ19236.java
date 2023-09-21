import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19236 {

	static Fish[][] map = new Fish[4][4];

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static int maxResult = Integer.MIN_VALUE;
	static int result;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				Fish fish = new Fish(i, j, num, dir);
				map[i][j] = fish;
			}
		}

		Fish shark = new Fish(0, 0, 0, map[0][0].dir);
		result += map[0][0].num;
		map[0][0] = shark;
		simulation(map, shark, result);

		System.out.println(maxResult);
	}

	public static void simulation(Fish[][] map, Fish shark, int result) {

		if (maxResult < result) {
			maxResult = result;
		}

		fishesMove(map);

		// sharkMove
		int nx, ny;
		for (int i = 0; i < 3; i++) {
			nx = shark.x + dx[shark.dir];
			ny = shark.y + dy[shark.dir];

			if (!isIn(nx, ny)) { // 경계를 넘었거나, 물고기가 없다면
				continue;
			} else if (map[nx][ny] == null) {
				continue;
			}

			Fish newShark = new Fish(nx, ny, 0, map[nx][ny].dir);
			Fish[][] cloneMap = cloning(map);

			cloneMap[shark.x][shark.y] = null;

			cloneMap[nx][ny] = newShark;

			simulation(cloneMap, newShark, result + map[nx][ny].num);

			shark.x = nx;
			shark.y = ny;

		}

	}

	public static Fish[][] cloning(Fish[][] map) {
		Fish[][] clone = new Fish[4][4];

		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < 4; j++) {
				clone[i][j] = map[i][j];
			}
		}

		return clone;
	}

	public static boolean isIn(int nx, int ny) {

		if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
			return false;
		}

		return true;
	}

	public static void fishesMove(Fish[][] map) {

		for (int num = 1; num <= 16; num++) {

			breakpoint: for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (map[i][j] != null && map[i][j].num == num) {
						fishMove(map, map[i][j]);
						break breakpoint;
					}
				}
			}
		}
	}

	public static void fishMove(Fish[][] map, Fish fish) {

		int nx, ny;

		for (int i = 0; i < 8; i++) {
			nx = fish.x + dx[fish.dir];
			ny = fish.y + dy[fish.dir];
			if (!isIn(nx, ny)) { // 그 방향이 경계 밖이거나, 상어가 있는 경우

				fish.dir = (fish.dir + 1) % 8;

				continue;
			}

			if (map[nx][ny] != null && map[nx][ny].num == 0) {
				fish.dir = (fish.dir + 1) % 8;

				continue;
			}

			if (map[nx][ny] == null) { // 갈 곳이 빈 공간인 경우
				map[nx][ny] = fish;
				map[fish.x][fish.y] = null;
				fish.x = nx;
				fish.y = ny;
				return;

			} else { // 다른 물고기가 있는 경우
				Fish f1 = map[nx][ny];

				map[nx][ny] = fish;

				map[fish.x][fish.y] = f1;

				f1.x = fish.x;
				f1.y = fish.y;

				fish.x = nx;
				fish.y = ny;
				return;
			}

		}
	}

	public static void printMap(Fish[][] map) {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(map[i][j].num + " ");
			}
			System.out.println();
		}
	}

	public static class Fish {
		int x, y;
		int num;
		int dir;

		public Fish(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
}