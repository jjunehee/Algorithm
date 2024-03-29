import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17142 {
	static char[][] map;
	static int N, M;
	static List<Virus> vList = new ArrayList<>();
	static Virus[] active;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int minTime = Integer.MAX_VALUE;
	static int emptySpace;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		active = new Virus[M];
		map = new char[N][N];
		for (int i = 0; i < N; i++) { // map을 입력 0빈칸, 1벽, 2바이러스 놓을 수 있는 위치
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == '2') {
					vList.add(new Virus(i, j));
					map[i][j] = '*';
				} else if (map[i][j] == '1') {
					map[i][j] = 'w';
				} else if (map[i][j] == '0') {
					emptySpace++;
				}
			}
		}
		// M개 만큼 2번 자리에 바이러스를 배치
		pickM(0, 0);
		if(!flag) {
			System.out.println("-1");
		} else {
			System.out.println(minTime);
		}
	}

	private static void pickM(int cnt, int idx) { // 바이러스를 놓을 수 있는 위치 중에서 M개 만큼을 조합을 톹해 pick
		if (cnt == M) {
			spread();
			return;
		}

		for (int i = idx; i < vList.size(); i++) {
			active[cnt] = vList.get(i);
			pickM(cnt + 1, i + 1);
		}
	}

	private static void spread() {
		boolean allSpread = false;
		char[][] copyMap = new char[N][N];
		for (int i = 0; i < map.length; i++) {
			copyMap[i] = map[i].clone();
		}
		int time = 0;
		Queue<Virus> VirusQ = new LinkedList<>();
		Queue<Virus> totalQ = new LinkedList<>();
		for (Virus v : active) {
			copyMap[v.x][v.y] = 'V';
			totalQ.add(v);
		}
		int empty = emptySpace;

		while (empty != 0 && !totalQ.isEmpty()) {

			while (!totalQ.isEmpty()) {
				Virus v1 = totalQ.poll();
				VirusQ.add(v1);
			}
			time++;
			while (!VirusQ.isEmpty()) {
				Virus v = VirusQ.poll();
				int nx;
				int ny;
				for (int dir = 0; dir < 4; dir++) {
					nx = v.x + dx[dir];
					ny = v.y + dy[dir];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
						continue;
					}

					if (copyMap[nx][ny] == '0') {
						totalQ.add(new Virus(nx, ny));
						copyMap[nx][ny] = (char) (time + '0');
						empty--;
					} else if(copyMap[nx][ny] == '*') {
						totalQ.add(new Virus(nx, ny));
						copyMap[nx][ny] = (char) (time + '0');
					}
				}

			}

		}
		if (empty == 0) {
			allSpread = true;
		} else if (totalQ.isEmpty()) {
			allSpread = false;
		}
		
		if (allSpread) {
			minTime = Math.min(minTime, time);
			flag = true;
		}

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
