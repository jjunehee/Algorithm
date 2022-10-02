package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main17144 {
	public static int R;
	public static int C;
	public static int[][] map;
	public static ArrayList<Machine> machineList = new ArrayList<>();
	public static Machine up;
	public static Machine down;
	public static int[][] dustChange;
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < R; i++) { // 공청기 추가
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1 && map[i + 1][j] == -1) {
					up = new Machine(i, 0);
					map[i][j] = 0;
					map[i + 1][j] = 0;
				}
			}
		}
		int time = 0;
		while (time > T) {
			dustSpread();
			up.activate();
			down.activate();
			time++;
		}

	}

	private static void dustSpread() {
		dustChange = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0) {
					int count = aroundChange(i, j, map[i][j] / 5);
					dustChange[i][j] -= map[i][j] / 5 * count;
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += dustChange[i][j];
			}
		}
	}

	private static int aroundChange(int i, int j, int value) {
		int count = 0;
		for (int dir = 0; dir < 4; dir++) {
			int aroundX = i + dx[dir];
			int aroundY = j + dy[dir];

			if (aroundX < 0 || aroundX >= R || aroundY < 0 || aroundY >= C) {
				continue;
			}
			dustChange[aroundX][aroundY] += value;
			count++;
		}
		return count;
	}

	public static class Machine {
		int x;
		int y;

		public Machine(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void activate() {
			upArea();
			downArea();
		}

		private void upArea() {
			int endPosX = up.x;
			int endPosY = up.y;
			while (true) {
				int dir = 2;
				int nx = up.x + dx[dir];
				int ny = up.y + dy[dir];

				if (nx < 0 || nx >= R || ny < 0 || ny >= R) {
					dir = (dir + 3) % 4;
					nx = up.x + dx[dir];
					ny = up.y + dy[dir];
				}

				map[up.x][up.y] = map[nx][ny];
				up.x = nx;
				up.y = ny;
				if (up.x == endPosX && up.y == endPosY) {
					break;
				}
			}
			
		}

		private void downArea() {

		}
	}
}
