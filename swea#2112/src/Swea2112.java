import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 보호 필름
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
			
			
			Flag = false;
			num = 0;
			
			copyMap = new int[D][W];
			for (int i = 0; i < D; i++) {
				copyMap[i] = map[i].clone();
			}
			
			if (K==1 || test()) { // 합격기준이 1이면 바로 합격 or 약품투입 해보지않고 바로 test()했을때, 합격 나오면 투입 X
				Flag = true;
			} else {
				num++;
			}
			
			//약품 투입 조합 start
			while (!Flag) {
				
				selectRow = new int[num];
				combRow(0, 0); // 약품 투입 조합 start
				
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
		if (Flag) {
			return;
		}
		if (cnt == num || cnt==K) {
			tempValue = new int[num];
			combAB(0);
			return;
		}
		
		if(cnt > K) {
			return;
		}
		for (int i = idx; i < D; i++) {
			selectRow[cnt] = i;
			combRow(cnt + 1, i + 1);
		}

	}

	private static void combAB(int cnt) {

		if (Flag) {
			return;
		}
		if (cnt == num) {
			copyMap = new int[D][W];
			for (int i = 0; i < D; i++) {
				copyMap[i] = map[i].clone();
			}
			
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < W; j++) {
					copyMap[selectRow[i]][j] = tempValue[i];
				}
			}
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
			for (int i = 0; i <= D-K; i++) {
				check(i, j);
				if (Flag) {
					break;
				} else {
					continue;
				}
			}
			
			if(Flag) {
				cnt++;
			}
		}
		if(cnt==W) {
			Flag = true;
			return true;
		}
		Flag = false;
		return false;
	}

	private static void check(int i, int j) {
		
		for (int t = 0; t < K; t++) {
			if (copyMap[i][j] == copyMap[i + t][j]) {
				Flag = true;
				continue;
			}
			Flag = false;
			return;
		}

	}
}