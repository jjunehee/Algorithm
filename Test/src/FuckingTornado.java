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

	}

	private static void simulation() {

		// 토네이도 생성
		tornado = new Tornado(N / 2 + 1, N / 2 + 1, 1, 0, 0, 0);
		while (true) {
			if (tornado.x == 1 && tornado.y == 1) {
				break;
			}
			tornadoMove();

			blow();
		}

	}

	private static void tornadoMove() {

		tornado.x += dx[tornado.dir];
		tornado.y += dy[tornado.dir];
		tornado.distCnt++;

		System.out.println(tornado.x + " " + tornado.y);


		if (tornado.distCnt == tornado.dist) {
			tornado.rotateCnt++;
			tornado.dir = (tornado.dir + 1) % 4;
			tornado.distCnt = 0;
			if ((tornado.rotateCnt) % 2 == 0) {
				tornado.dist++;
			}
		}

		

	}

	private static void blow() {

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
