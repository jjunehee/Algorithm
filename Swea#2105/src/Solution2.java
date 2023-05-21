import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {

	static int N;
	static int[][] map;

	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { 1, -1, -1, 1 };
	static int ret;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[101];
			ret = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int dir = 0; dir < 4; dir++) {
						simulation(i, j, i, j, dir, 0, 0);
					}
				}
			}
			if (ret == Integer.MIN_VALUE) {
				sb.append(-1).append("\n");
			} else {
				sb.append(ret).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void simulation(int x, int y, int eX, int eY, int dir, int length, int rotateCnt) {

		if (rotateCnt >= 4) {
			return;
		}
		if (length > 0 && x == eX && y == eY) {
			ret = Math.max(ret, length);
			return;
		}

		// 정면
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[map[nx][ny]]) {
			visited[map[nx][ny]] = true;
			simulation(nx, ny, eX, eY, dir, length + 1, rotateCnt);
			visited[map[nx][ny]] = false;
		}

		// 우측
		dir = (dir + 1) % 4;
		nx = x + dx[dir];
		ny = y + dy[dir];
		if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[map[nx][ny]]) {
			visited[map[nx][ny]] = true;
			simulation(nx, ny, eX, eY, dir, length + 1, rotateCnt + 1);
			visited[map[nx][ny]] = false;
		}

	}
}
