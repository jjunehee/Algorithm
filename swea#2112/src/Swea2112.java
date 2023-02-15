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
	static int[] tempValue;

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

			num = 0;
			copyMap = new int[D][W];
			for (int i = 0; i < D; i++) {
				copyMap[i] = map[i].clone();
			}
			if (test()) {
				Flag = true;
			} else {
				num++;
			}
			while (true) {
				selectRow = new int[num];
				Flag = true;
				combRow(0, 0);
				if (Flag) {
					break;
				}
				num++;
			}
			sb.append(num).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void combRow(int cnt, int idx) {
		if (!Flag) {
			System.out.println("q");
			return;
		}
		if (cnt == num) {
			System.out.println("c");
			tempValue = new int[num];
			combAB(0);
			return;
		}
		for (int i = idx; i < D; i++) {
			Flag = true;
			selectRow[cnt] = i;
			combRow(cnt + 1, i + 1);
		}

	}

	private static void combAB(int cnt) {

		if (!Flag) {
			return;
		}
		if (cnt == num) {
			System.out.println("num : " + num + " tttttttttttttttttttttttt");
			copyMap = new int[D][W];
			for (int i = 0; i < D; i++) {
				copyMap[i] = map[i].clone();
			}
			
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < W; j++) {
					copyMap[selectRow[i]][j] = tempValue[i];
				}
			}
			
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(copyMap[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("---------------------");
			
			test();
			return;
		}

		// 중복 순열
		// SelectedRow에는 cnt크기만큼 뽑은 행 정보가 저장되어 있다.
		for (int i = 0; i < 2; i++) {
			tempValue[cnt] = i;
			combAB(cnt + 1);
		}

	}

	private static boolean test() {
		int cnt = 0;
		for (int j = 0; j < W; j++) {
			Flag = true;
			for (int i = 0; i <= D - K; i++) {
				check(i, j);
				if (!Flag) {
					break;
				}
			}
			
			if(Flag) { // 해당 열이 check에 통과하게되면 합격기준 cnt++;
				cnt++;
			}
		}
		if(cnt>=K) {
			Flag = true;
			return true;
		}
		Flag = false;
		return false;
	}

	private static void check(int i, int j) {
		
		for (int t = 0; t < 3; t++) {
			if (copyMap[i][j] == copyMap[i + t][j]) {
				Flag = true;
				continue;
			}
			Flag = false;
			break;
		}

	}
}