import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16926 {
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean flag;
	static boolean[][] visited;
	static boolean[][] VISIT;
	static int index;
	static int x, y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		VISIT = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<Element> q = new LinkedList<>();
		int dir = 0;
		x = 0;
		y = 0;
		index = 0;
		int cnt = 0;
		while (true) {

			q.add(new Element(x, y, map[x][y]));

			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
				dir = (dir + 1) % 4;
				nx = x + dx[dir];
				ny = y + dy[dir];
			}
			visited[nx][ny] = true;

			if (nx == index && ny == index) {
				for (int i = 0; i < R; i++) {
					q.add(q.poll());
				}
				x = index;
				y = index;
				int NX = x;
				int NY = y;
				dir = 0;

				while (!q.isEmpty()) {
					map[x][y] = q.poll().value;
					NX = x + dx[dir];
					NY = y + dy[dir];
					if (NX < 0 || NX >= N || NY < 0 || NY >= M || VISIT[NX][NY]) {
						dir = (dir + 1) % 4;
						NX = x + dx[dir];
						NY = y + dy[dir];
					}
					VISIT[NX][NY] = true;
					x = NX;
					y = NY;
				}
				cnt++;
				if (cnt < Math.min(N, M) / 2) {
					index++;
					x = index;
					y = index;
					dir = 0;
					flag = true;
					continue;
				}
				break;
			}

			x = nx;
			y = ny;

		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static class Element {
		int x;
		int y;
		int value;

		public Element(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}
