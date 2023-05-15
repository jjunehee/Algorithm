import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[][] map;
	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { 1, -1, -1, 1 };
	static int N;
	static boolean[] check;
	static int ret = -1;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			ret = -1;
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					int nx, ny;
					for (int dir = 0; dir < 4; dir++) {
						nx = i + dx[dir];
						ny = j + dy[dir];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
							continue;
						}
						check = new boolean[101];
						check[map[nx][ny]] = true;
						DFS(i, j, nx, ny, 1, dir);
					}
				}
			}

			sb.append(ret).append("\n");

		}

		System.out.println(sb.toString());
	}

	public static void DFS(int eX, int eY, int x, int y, int depth, int dir) {
		
		if(eX == 5 && eY == 5) {
			System.out.println(x + " " + y + " " + depth);
		}
		
		if (x == eX && y == eY) {
//			if(depth == 10) {
//				System.out.println(eX +  " " + eY);
//			}
			ret = Math.max(depth, ret);
		}

		int nx, ny;
		// 정면
		nx = x + dx[dir];
		ny = y + dy[dir];

		if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
			if (!check[map[nx][ny]]) {
				check[map[nx][ny]] = true;
				DFS(eX, eY, nx, ny, depth + 1, dir);
				check[map[nx][ny]] = false;
			}
		}

		// 우측
		dir = (dir + 1) % 4;
		nx = x + dx[dir];
		ny = y + dy[dir];

		if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
			if (!check[map[nx][ny]]) {
				check[map[nx][ny]] = true;
				DFS(eX, eY, nx, ny, depth + 1, dir);
				check[map[nx][ny]] = false;
			}
		}


	}
}
