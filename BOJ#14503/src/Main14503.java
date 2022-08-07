package  src;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14503 {

	public static int[][] map;
	public static int count = 1;
	public static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st1.nextToken());
		M = Integer.parseInt(st1.nextToken());

		map = new int[N][M];
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
		robot.clean();
		System.out.println(count);

	}

	public static class Robot {
		private int x;
		private int y;
		private int dir;
		final int Wall = 1;
		final int dirt = 0;
        final int visited = 2;
		private int[] dx = { -1, 0, 1, 0 };
		private int[] dy = { 0, 1, 0, -1 };

		public Robot(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
            map[x][y] = visited;
		}

		void clean() {
			while (true) {
				boolean iscleanAround = false;
				for (int i = 0; i < 4; i++) {
					dir = (dir + 3) % 4;

					int nx = x + dx[dir];
					int ny = y + dy[dir];

					if (map[nx][ny] == dirt) {
						move(nx, ny);
						iscleanAround = true;
						break;
					}

				}

				if (!iscleanAround && !canBack()) {
					break;
				}
			}
		}

		private void move(int nx, int ny) {
			count++;
			this.x = nx;
			this.y = ny;
            map[this.x][this.y] = visited;
		}

		private boolean canBack() {
			int back = (dir + 2) % 4;
			int bx = x + dx[back];
			int by = y + dy[back];
			if(map[bx][by] != Wall) {
				this.x = bx;
				this.y = by;
				return true;
			}
			return false;
		}
	}

}