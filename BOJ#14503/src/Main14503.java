package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14503 {

	public static int[][] map;
	public static boolean[][] visited;
	public static int count = 1;
	public static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st1.nextToken());
		M = Integer.parseInt(st1.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		StringTokenizer st2 = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st2.nextToken());
		int c = Integer.parseInt(st2.nextToken());
		int d = Integer.parseInt(st2.nextToken());

		for (int i = 0; i < N; i++) {
			StringTokenizer st3 = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st3.nextToken());
			}
		}
		Robot robot = new Robot(r, c, d);

		while (robot.activate()) {

		}

		System.out.println(count);

	}

	public static class Robot {

		private int x;
		private int y;
		private int dir;
		private int[] dx = { 0, -1, 0, 1 };
		private int[] dy = { -1, 0, 1, 0 };

		public Robot(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;

		}

		public boolean activate() {
			if (search()) {
				return true;
			} else {
				return false;
			}
		}

		private void move(int nextX, int nextY) {
			this.x = nextX;
			this.y = nextY;
			count += 1;
			visited[this.x][this.y] = true;
		}

		public boolean search() {
			int nextX, nextY;
			for (int i = 0; i < 4; i++) {
				dir = (dir + 1) % 4;
				nextX = x + dx[dir];
				nextY = y + dy[dir];
				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
					continue;
				}
				if (map[nextX][nextY] == 0 && map[nextX][nextY] == 0 && visited[nextX][nextY] == false) {
					move(nextX, nextY);
					return true;
				}
			}
			if (map[x + dx[(dir + 2) % 4]][y + dy[(dir + 2) % 4]] == 0) {
				back();
				search();
			} else {
				return false;
			}
			return false;
		}

		private void back() {
			this.x = x + dx[(dir + 2) % 4];
			this.y = y + dy[(dir + 2) % 4];
		}

	}
}
