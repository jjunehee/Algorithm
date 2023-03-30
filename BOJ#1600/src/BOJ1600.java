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
	static int min;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(BFS());

	}

	public static int BFS() {
		Queue<Monkey> q = new LinkedList<>();
		Monkey monkey = new Monkey(0, 0, K);
		q.add(monkey);
		visited[0][0][K] = true;

		int time = 0;
		boolean flag = false;
		while (!q.isEmpty()) {

			int size = q.size();
			for (int i = 0; i < size; i++) {
				Monkey m = q.poll();
				if (m.x == H - 1 && m.y == W - 1) {
					min = time;
					flag = true;
					break;
				}

				for (int dir = 0; dir < 4; dir++) {

					int nx = m.x + dx[dir];
					int ny = m.y + dy[dir];

					// normal move
					if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] == 0 && !visited[nx][ny][m.horseMove]) {
						visited[nx][ny][m.horseMove] = true;
						q.add(new Monkey(nx, ny, m.horseMove));
					}

				}

				if (m.horseMove > 0) {
					for (int dir = 0; dir < 8; dir++) {
						// horse move
						int nx = m.x + hx[dir];
						int ny = m.y + hy[dir];

						if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] == 0 && !visited[nx][ny][m.horseMove-1]) {
							visited[nx][ny][m.horseMove - 1] = true;
							q.add(new Monkey(nx, ny, m.horseMove - 1));
						}
					}
				}
			}
			if (flag) {
				break;
			} else if (q.isEmpty()) {
				return -1;
			}
			time++;

		}

		return time;

	}

	public static class Monkey {
		int x;
		int y;
		int horseMove;

		public Monkey(int x, int y, int horseMove) {
			this.x = x;
			this.y = y;
			this.horseMove = horseMove;
		}
	}

}