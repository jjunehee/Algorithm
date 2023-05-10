import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static char[][] map;
	static boolean[][][] visited;
	static int N;

	static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static int startX, startY, startMode;
	static int endX, endY, endMode;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		boolean flag = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B') {
					if (i + 1 < N && map[i + 1][j] == 'B') {
						startMode = 2; // 세로모드
						startX = i + 1;
						startY = j;
					} else {
						startMode = 1; // 가로 모드
						startX = i;
						startY = j + 1;
					}
					flag = true;
					break;
				}

			}
			if (flag) {
				break;
			}
		}

		flag = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'E') {
					if (i + 1 < N && map[i + 1][j] == 'E') {
						endMode = 2; // 세로모드
						endX = i + 1;
						endY = j;
					} else {
						endMode = 1; // 가로 모드
						endX = i;
						endY = j + 1;
					}
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B' || map[i][j] == 'E') {
					map[i][j] = '0';
				}
			}
		}

		visited = new boolean[N][N][3];

		int result = BFS();

		System.out.println(result);
	}

	public static int BFS() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(startX, startY, startMode, 0));
		visited[startX][startY][startMode] = true; // 시작위치, 가로세모드 방문체크

		while (!q.isEmpty()) {

			Pos cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			if (cur.x == endX && cur.y == endY && cur.mode == endMode) {
				return cur.cnt;
			}

			int nx, ny;

			if (cur.mode == 1) { // 가로모드

				int rotate_cnt = 0;
				for (int dir = 0; dir < 8; dir++) { // 회전
					nx = x + dx[dir];
					ny = y + dy[dir];
					if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == '0' && !visited[x][y][2]) {
						
						rotate_cnt++;
					}
				}
				if(rotate_cnt == 8) {
					visited[x][y][2] = true;
					q.add(new Pos(x, y, 2, cur.cnt + 1));
				}

				// 상
				if (x - 1 >= 0 && y - 1 >= 0 && y + 1 < N && map[x - 1][y - 1] == '0' && map[x - 1][y] == '0'
						&& map[x - 1][y + 1] == '0' && !visited[x - 1][y][1]) {
					visited[x - 1][y][1] = true;
					q.add(new Pos(x - 1, y, 1, cur.cnt + 1));
				}

				// 하
				if (x + 1 < N && y - 1 >= 0 && y + 1 < N && map[x + 1][y - 1] == '0' && map[x + 1][y] == '0'
						&& map[x + 1][y + 1] == '0' && !visited[x + 1][y][1]) {
					visited[x + 1][y][1] = true;
					q.add(new Pos(x + 1, y, 1, cur.cnt + 1));
				}

				// 좌
				if (y - 2 >= 0 && map[x][y - 2] == '0' && !visited[x][y - 1][1]) {
					visited[x][y - 1][1] = true;
					q.add(new Pos(x, y - 1, 1, cur.cnt + 1));
				}

				// 우
				if (y + 2 < N && map[x][y + 2] == '0' && !visited[x][y + 1][1]) {
					visited[x][y + 1][1] = true;
					q.add(new Pos(x, y + 1, 1, cur.cnt + 1));
				}
			} else { // 세로모드
				int rotate_cnt = 0;
				for (int dir = 0; dir < 8; dir++) { // 회전
					nx = x + dx[dir];
					ny = y + dy[dir];
					if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == '0' && !visited[x][y][1]) {
						
						rotate_cnt++;
					}
				}
				if(rotate_cnt == 8) {
					visited[x][y][1] = true;
					q.add(new Pos(x, y, 1, cur.cnt + 1));
				}

				// 상
				if (x - 2 >= 0 && map[x - 2][y] == '0' && !visited[x - 1][y][2]) {
					visited[x - 1][y][2] = true;
					q.add(new Pos(x - 1, y, 2, cur.cnt + 1));
				}

				// 하
				if (x + 2 < N && map[x + 2][y] == '0' && !visited[x + 1][y][2]) {
					visited[x + 1][y][2] = true;
					q.add(new Pos(x + 1, y, 2, cur.cnt + 1));
				}

				// 좌
				if (y - 1 >= 0 && map[x - 1][y - 1] == '0' && map[x][y - 1] == '0' && map[x + 1][y - 1] == '0'
						&& !visited[x][y - 1][2]) {
					visited[x][y - 1][2] = true;
					q.add(new Pos(x, y - 1, 2, cur.cnt + 1));
				}

				// 우
				if (y + 1 < N && map[x - 1][y + 1] == '0' && map[x][y + 1] == '0' && map[x + 1][y + 1] == '0'
						&& !visited[x][y + 1][2]) {
					visited[x][y + 1][2] = true;
					q.add(new Pos(x, y + 1, 2, cur.cnt + 1));
				}

			}
		}

		return 0;

	}

	public static class Pos {
		int x, y;
		int mode;
		int cnt;

		public Pos(int x, int y, int mode, int cnt) {
			this.x = x;
			this.y = y;
			this.mode = mode;
			this.cnt = cnt;
		}
	}
}
