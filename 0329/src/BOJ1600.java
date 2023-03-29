import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {

	static int[][] map;
	static int[] hx = { 2, 1, -1, -2, -2, -1, 1, 2 };
	static int[] hy = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int K;
	static int H, W;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		BFS();

	}

	public static void BFS() {
		Queue<Monkey> q = new LinkedList<>();
		Monkey monkey = new Monkey(0, 0, K);
		q.add(monkey);

		while (!q.isEmpty()) {
			Monkey m = q.poll();

			int size = q.size();
			for (int i = 0; i < size; i++) {
				for (int dir = 0; dir < 4; dir++) {

					int nx = m.x + dx[dir];
					int ny = m.y + dy[dir];

					// normal move
					if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] == 0) {
						q.add(new Monkey(nx, ny, m.horseMove));
					}

					// horse move
					nx = m.x + hx[dir];
					ny = m.y + hy[dir];
					if(nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] == 0) {
						q.add(new Monkey(nx, ny, m.horseMove-1));
					}

				}
			}

		}

	}

	public static class Monkey {
		int x;
		int y;
		int dir;
		int horseMove;

		public Monkey(int x, int y, int horseMove) {
			this.x = x;
			this.y = y;
			this.horseMove = horseMove;
		}
	}

}
