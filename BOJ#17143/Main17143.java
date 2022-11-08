

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main17143 {
	public static int R, C;
	public static Shark[][] map;
	static ArrayList<Shark> sharkList = new ArrayList<>();
	static ArrayList<Shark> catchList = new ArrayList<>();
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new Shark[R + 1][C + 1];

		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r, c, s, d, z);
			sharkList.add(shark);
			map[r][c] = shark;
		}
		if (!sharkList.isEmpty()) {
			simulation();
		} else
			System.out.print("0");

	}

	private static void simulation() {
		int personPos = 0;

		while (true) {
			personPos++;
			if (personPos > C) {
				calculate();
				break;
			}
			hunt(personPos);
			sharkMove();
		}

	}

	private static void calculate() {
		int sum = 0;
		for (Shark shark : catchList) {
			sum += shark.size;
		}
		System.out.println(sum);
	}

	private static void sharkMove() {

		Shark[][] moveMap = new Shark[R + 1][C + 1];
		ArrayList<Shark> removeList = new ArrayList<>();
		for (int i = 0; i < sharkList.size(); i++) {
			Shark shark = sharkList.get(i);
			int nx = shark.x;
			int ny = shark.y;
			int X = shark.dir < 2 ? R : C;
			int speed = shark.speed % ((X - 1) * 2);
			for (int s = 0; s < speed; s++) {

				nx = nx + dx[shark.dir];
				ny = ny + dy[shark.dir];
				if (nx < 1) {
					shark.dir = 1;
					nx = nx + dx[shark.dir] * 2;
					ny = ny + dy[shark.dir] * 2;
				} else if (nx > R) {
					shark.dir = 0;
					nx = nx + dx[shark.dir] * 2;
					ny = ny + dy[shark.dir] * 2;
				} else if (ny < 1) {
					shark.dir = 2;
					nx = nx + dx[shark.dir] * 2;
					ny = ny + dy[shark.dir] * 2;

				} else if (ny > C) {
					shark.dir = 3;
					nx = nx + dx[shark.dir] * 2;
					ny = ny + dy[shark.dir] * 2;
				}

			}

			shark.x = nx;
			shark.y = ny;
			if (moveMap[nx][ny] != null) {
				if (moveMap[nx][ny].size < shark.size) {
					removeList.add(moveMap[nx][ny]);
					moveMap[nx][ny] = shark;
				} else {
					removeList.add(shark);
				}
			} else {
				moveMap[nx][ny] = shark;
			}
		}

		for (Shark shark : removeList) {
			sharkList.remove(shark);
		}

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				map[i][j] = moveMap[i][j];
			}
		}
	}

	private static void hunt(int huntColumn) {
		for (int i = 1; i <= R; i++) {
			if (map[i][huntColumn] != null) {
				catchList.add(map[i][huntColumn]);
				sharkList.remove(map[i][huntColumn]);
				map[i][huntColumn] = null;
				break;
			}
		}
	}

	public static class Shark {
		int x;
		int y;
		int speed;
		int dir;
		int size;

		public Shark(int x, int y, int speed, int dir, int size) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}

}
