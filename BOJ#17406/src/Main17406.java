import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17406 {
	static int[][] map;
	static Queue<Operator> q = new LinkedList<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int N, M;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			q.add(new Operator(r, c, s));
		}

		visited = new boolean[N + 1][M + 1];
		move();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void move() { // q에서 하나씩 빼면서 map을 변경해줄 메소드

		while (!q.isEmpty()) { // 존재하는 연산을 모두 마칠때까지 조져주자
			Operator op = q.poll(); // 첫번째 연산 꺼내

			// 돌리는 직사각형의 시작점과 끝점을 구해야지.
			int startX, startY;
			int endX, endY;

			startX = op.r - op.s;
			startY = op.c - op.s;

			endX = op.r + op.s;
			endY = op.c + op.s;

			// 자~ 이제 직사각형을 돌려주자
			rotate(startX, startY, endX, endY);

		}
	}

	private static void rotate(int x1, int y1, int x2, int y2) {

		int endX = x1;
		int endY = y1;
		int nx = x1;
		int ny = y1;
		int dir = 0;
//		System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
		Deque<Integer> q = new LinkedList<>();
		int cnt = 0;
		
		int x = x1;
		int y = y1;
		while (true) {
			q.add(map[x][y]);
			nx = x + dx[dir];
			ny = y + dy[dir];
			if (nx == endX && ny == endY) {
				
				q.add(q.pollLast());
				
				
				int X = x1;
				int Y = y1;
				dir = 0;
				while (!q.isEmpty()) {
					map[X][Y] = q.poll();
					System.out.println(X + " " + Y);
					nx = X + dx[dir];
					ny = Y + dy[dir];
					
					// nx, ny가 범위 밖을 벗어난다면, 방향 틀어주기
					if (nx < 0 || nx < x1 || nx > x2 || ny < 0 || ny < y1 || ny > y2 || nx > N || ny > M ) {
						dir = (dir + 1) % 4;
						nx = X + dx[dir];
						ny = Y + dy[dir];
					}

					X = nx;
					Y = ny;
				}
				
				
				
				break;
//				rotate(x1 + 1, y1 + 1, x2 - 1, y2 - 1);
			}
			
			// nx, ny가 범위 밖을 벗어난다면, 방향 틀어주기
			if (nx < 0 || nx < x1 || nx > x2 || ny < 0 || ny < y1 || ny > y2 || nx > N || ny > M || visited[nx][ny]) {
				dir = (dir + 1) % 4;
				nx = x + dx[dir];
				ny = y + dy[dir];
			}
			System.out.println(nx + " " + ny + "  value" + map[nx][ny]);

			visited[nx][ny] = true;
			x = nx;
			y = ny;
		}
	}

	public static class Operator {
		int r;
		int c;
		int s;

		public Operator(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}
