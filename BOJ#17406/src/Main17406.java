import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17406 {
	static int[][] map;
	static int[][] copyMap;
	static Operator[] opList;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int N, M, K;
	static boolean[] visited;
	static int[] sum;
	static Operator[] pick;
	static int min = Integer.MAX_VALUE;
	static int ccnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		copyMap = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		opList = new Operator[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			opList[i] = new Operator(r, c, s);
		}

		visited = new boolean[K];
		pick = new Operator[K];
		move(0);
		System.out.print(min);
	}

	private static void move(int cnt) { // q에서 하나씩 빼면서 map을 변경해줄 메소드

		if (cnt == K) { // pick이 모두 채워졌다 == 연산의 순서가 모두 정해져서 들어가졌다.
			ccnt++;
			for (int i = 1; i <= N; i++) {
				copyMap[i] = map[i].clone();
			}

			for (Operator op : pick) { // 연산 꺼내
				int startX, startY;
				int endX, endY;
				// 돌리는 직사각형의 시작점과 끝점을 구해야지.
				startX = op.r - op.s;
				startY = op.c - op.s;

				endX = op.r + op.s;
				endY = op.c + op.s;

				// 자~ 이제 직사각형을 돌려주자
				rotate(startX, startY, endX, endY);
			}
			
			sum = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					sum[i] += copyMap[i][j];
				}
				min = Math.min(min, sum[i]);
			}
			return;
		}

		for (int i = 0; i < K; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			pick[cnt] = opList[i];
			move(cnt + 1);
			visited[i] = false;
		}

	}

	private static void rotate(int x1, int y1, int x2, int y2) {
		int depth = Math.min(x2 - x1, y2 - y1) / 2;

		for (int d = 0; d < depth; d++) {
			int nowi = x1 + d, nowj = y1 + d;
			int tmp = copyMap[nowi][nowj];
			int dir = 0;
			while (true) {
				int nexti = nowi + dx[dir];
				int nextj = nowj + dy[dir];
				// 옆칸이 더이상 가면 안되는 칸
				if (nexti == x1 + d - 1 || nexti == x2 - d + 1 || nextj == y1 + d - 1 || nextj == y2 - d + 1) {
					dir++;
					if (dir == 4) {
						break; // 한바퀴 돌았네 옆칸이고 뭐고 그만해
					} else
						continue; // 바꾼 방향으로 다시 위로 가서 옆칸좌표 다시 계산해라.
				}

				// 음.. 옆칸이 가도 되는 칸인가보네 ? 옆칸에 있는 값 땡겨오고
				copyMap[nowi][nowj] = copyMap[nexti][nextj];
				nowi = nexti; // 나는 그칸으로 이동!
				nowj = nextj;
			}
			copyMap[x1 + d][y1 + d + 1] = tmp;
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
