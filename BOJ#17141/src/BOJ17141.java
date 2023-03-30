import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17141 {
	static String[][] map;
	static Virus[] ableVirus;
	static int N, M;
	static Virus[] pickVirus;
	static int ableVirusCnt;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int emptySpace;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ableVirus = new Virus[11];
		pickVirus = new Virus[M];

		map = new String[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken();
				if (map[i][j].equals("2")) {
					ableVirus[++ableVirusCnt] = new Virus(i, j);
				}
				if (map[i][j].equals("0") || map[i][j].equals("2")) {
					emptySpace++;
				}
				if (map[i][j].equals("1")) {
					map[i][j] = "-";
				}
			}
		}
		// 이제 ableVirus중에서 M개를 뽑아야한다.
		pickM(1, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	// 조합(comb)을 사용해서 M개의 바이러스를 뽑자
	private static void pickM(int idx, int cnt) {

		if (cnt == M) {
			// M개의 바이러스 뽑기 완료.
			// Spread 시작
			int value = spread(emptySpace);
			if (value != -1) {
				min = Math.min(value, min);
			}
			return;
		}

		for (int i = idx; i <= ableVirusCnt; i++) {
			pickVirus[cnt] = ableVirus[i];
			pickM(i + 1, cnt + 1);
		}
	}

	private static int spread(int empty) {
		Queue<Virus> q = new LinkedList<>();
		// 뽑은 M개의 바이러스를 q에 넣기 (q는 바이러스 퍼뜨리는 것을 구현하기 위해)
		for (int i = 0; i < M; i++) {
			q.add(pickVirus[i]);
		}

		String[][] copyMap = new String[N][N];
		for (int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
		}

		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < M; i++) {
			copyMap[pickVirus[i].x][pickVirus[i].y] = "V";
			visited[pickVirus[i].x][pickVirus[i].y] = true;
		}

		empty = empty - M;
		if(empty ==0) {
			return 0;
		}
		int time = 1;
		while (!q.isEmpty()) {

			int size = q.size();
			for (int i = 0; i < size; i++) {
				Virus virus = q.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nx = virus.x + dx[dir];
					int ny = virus.y + dy[dir];

					if (nx >= 0 && nx < N && ny >= 0 && ny < N
							&& (copyMap[nx][ny].equals("0") || copyMap[nx][ny].equals("2")) && !visited[nx][ny]) {

						visited[nx][ny] = true;
						copyMap[nx][ny] = Integer.toString(time); // 그 자리에 바이러스가 퍼진 시간 입력
						q.add(new Virus(nx, ny)); // 다음 시간 spread를 위해 q에 추가
						empty--; // 빈칸 총 개수 -1
					}
					if (empty == 0) {
						return time;
					}
				}
			}
			time++;
		}

		return -1;
	}

	public static class Virus {
		int x;
		int y;

		public Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
