import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520 {
	static int N, M;
	static int[][] map;
	static int[][] dp;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		dp = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		System.out.println(dfs(1, 1));

	}

	public static int dfs(int x, int y) {
		if (x == N && y == M) {
			return 1;
		}

		if (dp[x][y] != -1) {
			return dp[x][y];
		}

		dp[x][y] = 0;

		int nx, ny;
		for (int dir = 0; dir < 4; dir++) {
			nx = x + dx[dir];
			ny = y + dy[dir];

			if (isBound(nx, ny)) {
				continue;
			}
			
			if(map[x][y] > map[nx][ny]) {
				dp[x][y] += dfs(nx,ny);
			}
		}
		
		return dp[x][y];

	}

	public static boolean isBound(int nx, int ny) {

		if (nx < 1 || nx > N || ny < 1 || ny > M) {
			return true;
		}

		return false;
	}
}
