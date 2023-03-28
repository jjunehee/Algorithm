import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {
	static int[][] map;
	static int N;
	static int ret;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(1, 2, 0);
		System.out.println(ret);

	}

	public static void DFS(int x, int y, int dir) {
		if (x == N && y == N) {
			ret++;
			return;
		}

		switch (dir) {
		case 0: // 가로로 움직일떄
			if (y + 1 <= N && map[x][y + 1] != 1) {
				DFS(x, y + 1, 0);
			}
			if (x + 1 <= N && y + 1 <= N && map[x + 1][y] == 0 && map[x][y + 1] == 0 && map[x + 1][y + 1] != 1) {
				DFS(x + 1, y + 1, 2);
			}
			break;
		case 1: // 세로로 움직일떄
			if (x + 1 <= N && map[x + 1][y] == 0) {
				DFS(x + 1, y, 1);
			}
			if (x + 1 <= N && y + 1 <= N && map[x + 1][y] == 0 && map[x][y + 1] == 0 && map[x + 1][y + 1] == 0) {
				DFS(x + 1, y + 1, 2);
			}
			break;
		case 2: // 대각선으로 움직일때
			if (y + 1 <= N && map[x][y + 1] == 0) {
				DFS(x, y + 1, 0);
			}
			if (x + 1 <= N && map[x + 1][y] == 0) {
				DFS(x + 1, y, 1);
			}
			if (x + 1 <= N && y + 1 <= N && map[x + 1][y] == 0 && map[x][y + 1] == 0 && map[x + 1][y + 1] == 0) {
				DFS(x + 1, y + 1, 2);
			}
			break;
		}
	}

}
