import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static char[][] map;
	static int N;
	static int min = Integer.MAX_VALUE;

	static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
				}
			}

			min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
						click(i, j, 1);
					}
				}
			}
			
			sb.append(min).append("\n");

		}
		
		System.out.println(sb.toString());
	}

	public static void click(int x, int y, int cnt) {

		if (cnt > min) {
			return;
		}

		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(x, y));

		System.out.println("=================");
		while (!q.isEmpty()) {
			
			
			Pos cur = q.poll();
			System.out.println(cur.x + " " + cur.y);
			int nx, ny;
			int count = 0;
			for (int dir = 0; dir < 8; dir++) {
				nx = cur.x + dx[dir];
				ny = cur.y + dy[dir];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					count++;
					continue;
				}

				if (map[nx][ny] == '.') {
					count++;
				} else {
					break;
				}
			}

			if (count == 8) {
				for (int dir = 0; dir < 8; dir++) {
					nx = cur.x + dx[dir];
					ny = cur.y + dy[dir];

					if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
						q.add(new Pos(nx, ny));
					}
				}
			}
			map[cur.x][cur.y] = (char) (count + '0');
		}
		
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '.') {
					flag = false;
					click(i, j, cnt + 1);
				}
			}
		}
		
		if (flag) {
			min = Math.min(cnt, min);
			return;
		}

	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
