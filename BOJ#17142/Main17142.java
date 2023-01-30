import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main17142 {
	static int[][] map;
	static int M;
	static List<Virus> vList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) { // map을 입력 0빈칸, 1벽, 2바이러스 놓을 수 있는 위치
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					vList.add(new Virus(i, j));
				}
			}
		}

		// M개 만큼 2번 자리에 바이러스를 배치
		pickM(0);

	}

	private static void pickM(int num) { // 바이러스를 놓을 수 있는 위치 중에서 M개 만큼을 조합을 톹해 pick
		if (num == M) {
			spread();
		}

		for (int i = 0; i < vList.size(); i++) {

		}
	}

	private static void spread() {

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
