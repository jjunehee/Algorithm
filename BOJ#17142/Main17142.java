import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main17142 {
	static int[][] map;
	static int N, M;
	static ArrayList<Virus> list = new ArrayList<>();
	static Virus[] active;
	static int count;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		active = new Virus[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					list.add(new Virus(i, j));
				}
			}
		}
		pickN(0, 0);
	}

	private static void pickN(int num, int start) { // DFS
		if (num == M) {
			spreadSimulation();
			return;
		}

		for (int i = start; i < list.size(); i++) {
			active[num] = list.get(i);
			pickN(num + 1, i + 1);
		}

	}

	private static void spreadSimulation() {
		// M개의 바이러스 모두 동시에 퍼져야함
		boolean endFlag = false;
		while(!endFlag) {
			
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
