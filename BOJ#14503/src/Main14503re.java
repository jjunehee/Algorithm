package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14503re {
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int cnt;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Robot robot = new Robot(r, c, d);

		if (map[r][c] == 0) {
			map[r][c] = 3;
			cnt++;
		}
		// 로봇이 움직임이 가능할 때 까지.
		while (robot.canMove()) {
			cnt++;
			map[robot.r][robot.c] = 3;
			System.out.println("dir" + robot.d);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println(cnt);
		}

		System.out.println(cnt);

	}

	public static class Robot {
		int r;
		int c;
		int d;

		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		// 움직일 수 있다는 것은..
		public boolean canMove() {
			// 주변 4칸 중 청소되지 않은 빈칸이 없고, 뒤로도 갈 수 없는 경우!
			if (checkDir()) { // 주변 4칸 중 조건을 만족하는 것이 존재한다면
				
				d = (d + 3) % 4; // 방향을 반시계 방향으로 틀고
				int nx = r + dx[d];
				int ny = c + dy[d];
				// 그 방향기준 앞쪽 칸이 청소되지 않은 빈칸인 경우 전진!
				if (map[nx][ny] == 0 && nx >= 0 && nx < N && ny >= 0 && ny < M) {
					r = nx;
					c = ny;
				}
				return true;
			} else {
				if (canMoveBack()) {
					d = (d + 2) % 4;
					this.r = this.r + dx[d];
					this.c = this.c + dy[d];
					return true;
				} else {
					System.out.println("Q");
					return false;
				}

			}
		}

		private boolean canMoveBack() {
			d = (d + 2) % 4;
			int nx = this.r + dx[d];
			int ny = this.c + dy[d];
			
			if (map[nx][ny] != 1 && nx >= 0 && nx < N && ny >= 0 && ny < M) {
				return true;
			}
			return false;
		}

		public boolean checkDir() {

			for (int dir = 0; dir < 4; dir++) {
				int nx = this.r + dx[dir];
				int ny = this.c + dy[dir];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
					return true;
				}
			}
			return false;
		}

	}
}
