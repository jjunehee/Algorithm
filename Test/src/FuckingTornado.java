import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FuckingTornado {
	static int[][] map;
	static int N;
	static Tornado tornado;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int ret;
	static int dir;

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

		simulation();
		System.out.println(ret);
	}

	private static void simulation() {

		// 토네이도 생성
		tornado = new Tornado(N / 2 + 1, N / 2 + 1, 1, 0, 0, 0);
		while (true) {
			if (tornado.x == 1 && tornado.y == 1) {
				break;
			}
			tornado.x += dx[tornado.dir];
			tornado.y += dy[tornado.dir];
			dir = tornado.dir;
			tornado.distCnt++;

			if (tornado.distCnt == tornado.dist) {
				tornado.rotateCnt++;
				tornado.dir = (tornado.dir + 1) % 4;
				tornado.distCnt = 0;
				if ((tornado.rotateCnt) % 2 == 0) {
					tornado.dist++;
				}
			}
			blow(tornado.x, tornado.y, dir);
		}

	}

	private static void blow(int x, int y, int dir) {

		// x,y 토네이도가 불어닥칠 먼지가 있는 자리

		int[][] plus = new int[N + 1][N + 1];
		int sum = 0;
		int nx, ny;

		// 앞앞
		nx = x + dx[dir] + dx[dir];
		ny = y + dy[dir] + dy[dir];
		if (nx < 1 || nx > N || ny < 1 || ny > N) { // 경계를 넘어갔다면
			ret += map[x][y] * 5 / 100;
		} else { // 경계를 안넘어갔다면
			map[nx][ny] += map[x][y] * 5 / 100;
		}
		sum += map[x][y] * 5 / 100;

		// 앞왼
		nx = x + dx[dir] + dx[(dir + 1) % 4];
		ny = y + dy[dir] + dy[(dir + 1) % 4];
		if (nx < 1 || nx > N || ny < 1 || ny > N) { // 경계를 넘어갔다면
			ret += map[x][y] * 10 / 100;
		} else { // 경계를 안넘어갔다면
			map[nx][ny] += map[x][y] * 10 / 100;
		}
		sum += map[x][y] * 10 / 100;

		// 앞오
		nx = x + dx[dir] + dx[(dir + 3) % 4];
		ny = y + dy[dir] + dy[(dir + 3) % 4];
		if (nx < 1 || nx > N || ny < 1 || ny > N) { // 경계를 넘어갔다면
			ret += map[x][y] * 10 / 100;
		} else { // 경계를 안넘어갔다면
			map[nx][ny] += map[x][y] * 10 / 100;
		}
		sum += map[x][y] * 10 / 100;

		// 왼
		nx = x + dx[(dir + 1) % 4];
		ny = y + dy[(dir + 1) % 4];
		if (nx < 1 || nx > N || ny < 1 || ny > N) { // 경계를 넘어갔다면
			ret += map[x][y] * 7 / 100;
		} else { // 경계를 안넘어갔다면
			map[nx][ny] += map[x][y] * 7 / 100;
		}
		sum += map[x][y] * 7 / 100;

		// 오
		nx = x + dx[(dir + 3) % 4];
		ny = y + dy[(dir + 3) % 4];
		if (nx < 1 || nx > N || ny < 1 || ny > N) { // 경계를 넘어갔다면
			ret += map[x][y] * 7 / 100;
		} else { // 경계를 안넘어갔다면
			map[nx][ny] += map[x][y] * 7 / 100;
		}
		sum += map[x][y] * 7 / 100;

		// 왼왼
		nx = x + dx[(dir + 1) % 4] + dx[(dir + 1) % 4];
		ny = y + dy[(dir + 1) % 4] + dy[(dir + 1) % 4];
		if (nx < 1 || nx > N || ny < 1 || ny > N) { // 경계를 넘어갔다면
			ret += map[x][y] * 2 / 100;
		} else { // 경계를 안넘어갔다면
			map[nx][ny] += map[x][y] * 2 / 100;
		}
		sum += map[x][y] * 2 / 100;

		// 오오
		nx = x + dx[(dir + 3) % 4] + dx[(dir + 3) % 4];
		ny = y + dy[(dir + 3) % 4] + dy[(dir + 3) % 4];
		if (nx < 1 || nx > N || ny < 1 || ny > N) { // 경계를 넘어갔다면
			ret += map[x][y] * 2 / 100;
		} else { // 경계를 안넘어갔다면
			map[nx][ny] += map[x][y] * 2 / 100;
		}
		sum += map[x][y] * 2 / 100;

		// 왼 왼+1
		nx = x + dx[(dir + 1) % 4] + dx[(dir + 2) % 4];
		ny = y + dy[(dir + 1) % 4] + dy[(dir + 2) % 4];
		if (nx < 1 || nx > N || ny < 1 || ny > N) { // 경계를 넘어갔다면
			ret += map[x][y] * 1 / 100;
		} else { // 경계를 안넘어갔다면
			map[nx][ny] += map[x][y] * 1 / 100;
		}
		sum += map[x][y] * 1 / 100;

		// 오 오+1
		nx = x + dx[(dir + 3) % 4] + dx[(dir + 2) % 4];
		ny = y + dy[(dir + 3) % 4] + dy[(dir + 2) % 4];
		if (nx < 1 || nx > N || ny < 1 || ny > N) { // 경계를 넘어갔다면
			ret += map[x][y] * 1 / 100;
		} else { // 경계를 안넘어갔다면
			map[nx][ny] += map[x][y] * 1 / 100;
		}
		sum += map[x][y] * 1 / 100;

		// 앞 계산
		nx = x + dx[dir];
		ny = y + dy[dir];
		if (nx < 1 || nx > N || ny < 1 || ny > N) { // 경계를 넘어갔다면
			ret += map[x][y] - sum;
		} else { // 경계를 안넘어갔다면
			map[nx][ny] += map[x][y] - sum;
		}

	}

	public static class Tornado {
		int x, y;
		int dist;
		int rotateCnt;
		int distCnt;
		int dir;

		public Tornado(int x, int y, int dist, int rotateCnt, int distCnt, int dir) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.rotateCnt = rotateCnt;
			this.distCnt = distCnt;
			this.dir = dir;
		}
	}
}
