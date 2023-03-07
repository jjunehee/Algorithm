import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;


//보급로
public class Swea1249 {
	static int[][] map;
	static int N;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

			sb.append(dijkstra()).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int dijkstra() {

		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		int[][] minTime = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minTime[i][j] = Integer.MAX_VALUE;
			}
		}

		minTime[0][0] = 0;
		pq.offer(new int[] { 0, 0, minTime[0][0] });

		int[] cur = null;
		int nx, ny;
		int curX, curY;
		int minValue;

		while (true) {
			cur = pq.poll();
			curX = cur[0];
			curY = cur[1];
			minValue = cur[2];

			if (visited[curX][curY]) {
				continue;
			}

			visited[curX][curY] = true;

			if (curX == N - 1 && curY == N - 1) {
				return minValue;
			}

			for (int dir = 0; dir < 4; dir++) {
				nx = curX + dx[dir];
				ny = curY + dy[dir];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}

				if (minTime[nx][ny] > minValue + map[nx][ny]) {
					minTime[nx][ny] = minValue + map[nx][ny];
					pq.offer(new int[] { nx, ny, minTime[nx][ny] });
				}
			}

		}

	}
}
