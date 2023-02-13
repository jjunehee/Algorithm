import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea2112 {
	static int[][] map;
	static boolean Flag;
	static int num;
	static int D, W, K;
	static int[] selectRow;
	static int[][] copyMap;
	static int[] Row;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			copyMap = new int[D][W];
			for (int i = 0; i < D; i++) {
				copyMap[i] = map[i].clone();
			}
			
			if (test()) {
				Flag = true;
			} else {
				num++;
			}
			while (!Flag) {

				selectRow = new int[num];
				combRow(0, 0);

				if (Flag) {
					break;
				}
				num++;
			}

		}

	}

	private static void combRow(int cnt, int idx) {
		if (!Flag) {
			return;
		}
		
		if (cnt == num) {

			Row = new int[num];
			combAB(0, 0);
			return;
		}

		for (int i = idx; i < D; i++) {
			selectRow[cnt] = i;
			combRow(cnt + 1, i + 1);
		}
		
	}

	private static void combAB(int cnt, int idx) {
		if (!Flag) {
			return;
		}

		if (cnt == num) {
			change();
			test();
			return;
		}

		if (idx == num) {
			return;
		}

		Row[selectRow[idx]] = 0;
		combAB(cnt + 1, idx + 1);
		Row[selectRow[idx]] = 1;
		combAB(cnt + 1, idx + 1);
	}

	private static void change() {
		copyMap = new int[D][W];
		for (int i = 0; i < D; i++) {
			copyMap[i] = map[i].clone();
		}
		for (int r = 0; r < num; r++) {
			for (int c = 0; c < W; c++) {
				copyMap[selectRow[r]][c] = Row[r];
			}
		}
	}

	private static boolean test() {

		for (int j = 0; j < W; j++) {
			for (int i = 0; i <= D - K; i++) {
				check(i, j);
				if (!Flag) {
					return false;
				}
			}
		}
		return true;
	}

	private static void check(int i, int j) {

		for (int k = 0; k < K; k++) {
			if (copyMap[i][j] == copyMap[i + k][j]) {
				Flag = true;
				continue;
			}
			Flag = false;
			break;
		}

	}

}
