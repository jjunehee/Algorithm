
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17142 {
	static int[][] map;
	static int N, M;
	static ArrayList<Virus> vList = new ArrayList<>();
	static Queue<Virus> activeQ = new LinkedList<>();
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) { // Map 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 2) { // 바이러스를 놓을 수 있는 위치라면
					vList.add(new Virus(i, j)); // 바이러스 정보 arrayList에 추가
				}
			}
		}

		// M개의 바이러스를 골라서 simulation을 돌려보며 최소 시간을 정한다.
		pickM(0, 0);
	}

	private static void pickM(int idx, int cnt) { // DFS를 사용해서 M개 고르기
		if (cnt == M) {
			simulation(); // 선택한 M개를 가지고 시뮬레이션
			return;
		}

		// 조합을 통해 M개의 바이러스 고르기
		for (int i = idx; i < vList.size(); i++) {
			activeQ.add(vList.get(i));
			pickM(i + 1, cnt + 1);
		}

	}

	private static void simulation() {

		// BFS를 사용하여 바이러스 spread 구현
		int time = 0;
		while (!activeQ.isEmpty()) { // 바이러스가 더이상 퍼질 수 있는 지 체크
			Virus v = activeQ.poll();

			for (int dir = 0; dir < 4; dir++) {
				// 사방에 0(바이러스가 퍼질 수 있는 공간)이 있는지 확인
				if (v.x + dx[dir] < 0) {

				}

			}

			time++;
		}
	}

	public static class Virus {
		int x, y; // 위치 정보

		public Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
