import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600_1 {

	static int[][] map;
	static int[] hx = { 2, 1, -1, -2, -2, -1, 1, 2 };
	static int[] hy = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int K;
	static int H, W;
	static int[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = Integer.MIN_VALUE;
			}
		}

		System.out.println(BFS());

	}

	public static int BFS() {
		Queue<Monkey> q = new LinkedList<>();
		Monkey monkey = new Monkey(0, 0, K);
		q.add(monkey);
		visited[0][0] = K;
		
		int time = 0;
		while (!q.isEmpty()) {

			int size = q.size();
			for (int i = 0; i < size; i++) {
				Monkey m = q.poll();
				if (m.x == H - 1 && m.y == W - 1) {
					return time;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nx = m.x + dx[dir];
					int ny = m.y + dy[dir];
					// normal move
					// [nx][ny] 위치에 남은 스킬 횟수를 의미. 그러므로 visited[nx][ny]에 기록된 값보다 작은 m.horseMove가 들어온 다는 것은 스킬을 더 많이
					// 사용하고 온 녀석. 그러므로 더 불리함. 패스~ 더 큰 놈이 와야함.
					if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] == 0 && visited[nx][ny] < m.horseMove) { 
						visited[nx][ny] = m.horseMove;
						q.add(new Monkey(nx, ny, m.horseMove));
					}

				}

				if (m.horseMove > 0) {
					for (int dir = 0; dir < 8; dir++) {
						// horse move
						int nx = m.x + hx[dir];
						int ny = m.y + hy[dir];
						if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] == 0 && visited[nx][ny] < m.horseMove-1) {
							visited[nx][ny] = m.horseMove-1;
							q.add(new Monkey(nx, ny, m.horseMove - 1));
						}
					}
				}
			}
			time++;

		}

		return -1;

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