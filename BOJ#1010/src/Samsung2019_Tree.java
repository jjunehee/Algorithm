
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//나무타이쿤
public class Samsung2019_Tree {

	static int N, M, D, P;
	static int[][] map;
	static Queue<Material> mList;
	static List<Command> cList;
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static boolean[][] isMaterial;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// n,m
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		isMaterial = new boolean[N][N];

		// input map
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cList = new ArrayList<>();
		// d : 이동방향, p : 이동 거리, M년 만큼
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()) - 1;
			P = Integer.parseInt(st.nextToken());
			cList.add(new Command(D, P));
		}

		mList = new LinkedList<>();

		// 영양제 초기화
		for (int i = N - 2; i <= N - 1; i++) {
			for (int j = 0; j <= 1; j++) {
				mList.add(new Material(i, j));
				isMaterial[i][j] = true;
			}
		}

		int result = simulation();
		System.out.println(result);

	}

	public static int simulation() {

		int time = 0;

		while (true) {

			if (time >= M) {
				break;
			}

			// 영양제 이동.
			Move(time);
			// 1만큼 성장.
			Grow1();
			// 주변 1이상의 리브로수 만큼 해당 위치 성장
			Grow2();
			// 영양제 위치 제외하고 2이상인 리브로수 2잘라내고, 영양제 투여
			cutAndMaterial();

			time++;

		}


		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}

		return sum;
	}

	public static void Move(int time) {

		// 이동 명령어
		int dir = cList.get(time).d;
		int dist = cList.get(time).p;

		int size = mList.size();

		List<Material> nextList = new ArrayList<>();
		for (int i = 0; i < size; i++) {

			Material now = mList.poll();

			isMaterial[now.x][now.y] = false;

			int nx = 0;
			int ny = 0;

			for (int p = 0; p < dist; p++) {

				nx = now.x + dx[dir];
				ny = now.y + dy[dir];

				Material next = boundCheckAndMove(nx, ny);

				now.x = next.x;
				now.y = next.y;

			}
			nextList.add(new Material(now.x, now.y));

		}
		
		for(Material m : nextList) {
			mList.add(m);
			isMaterial[m.x][m.y] = true;
		}

	}

	public static void Grow1() {

		for (Material m : mList) {
			map[m.x][m.y]++;
		}

	}

	public static void Grow2() {

		for (Material m : mList) {

			int cnt = 0;

			int nx, ny;
			for (int dir = 1; dir < 8; dir += 2) {
				nx = m.x + dx[dir];
				ny = m.y + dy[dir];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}

				if (map[nx][ny] >= 1) {
					cnt++;
				}
			}

			map[m.x][m.y] += cnt;
		}
	}

	public static void cutAndMaterial() {

		int size = mList.size();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (!isMaterial[i][j]) {
					if (map[i][j] >= 2) {
						map[i][j] -= 2;
						isMaterial[i][j] = true;
						mList.add(new Material(i, j));
					}
				}

			}
		}

		for (int k = 0; k < size; k++) {
			Material m = mList.poll();
			isMaterial[m.x][m.y] = false;
		}

	}

	public static Material boundCheckAndMove(int nx, int ny) {

		if (nx < 0 || nx >= N || ny < 0 || ny >= N) { // 경계를 넘었어

			if (nx < 0) {
				nx = N - 1;
			}

			if (nx >= N) {
				nx = 0;
			}

			if (ny < 0) {
				ny = N - 1;
			}

			if (ny >= N) {
				ny = 0;
			}
		}

		return new Material(nx, ny);
	}

	public static class Command {
		int d, p;

		public Command(int d, int p) {
			this.d = d;
			this.p = p;
		}
	}

	public static class Material {
		int x, y;

		public Material(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
