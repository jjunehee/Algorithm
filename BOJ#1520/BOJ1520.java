import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1520 {
	static int N, M;
	static int[][] map;
	static int[][] dp;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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

		bw.write(dfs(1,1) + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}

	public static int dfs(int x, int y) {
		if (x == N && y == M) {
			return 1;
		}

		if (dp[x][y] != -1) { 
			/* 
			 * 그 곳이 저번에 계산 끝난 곳이라면 써먹자. 
			 * 결국 x,y에서 N,M까지의 경우의 수는 전에 구한 결과와 같을테니
			 */
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
				/* 
				 * x,y에서 갈 수 있는 경우의 수는 결국 그 자리에서 갈 수 있는 곳(nx,ny)에서 
				 * N,M으로 가는 경우의 수의 합과 같다.
				 */
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
