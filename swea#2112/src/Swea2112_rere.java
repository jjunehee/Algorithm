import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea2112_rere {
	static int D, W, K;
	static int[][] map;
	static int[][] tempMap;
	static int[] selectedRow;
	static int min = Integer.MAX_VALUE;

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
			tempMap = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); // 맵 입력
					tempMap[i][j] = map[i][j]; // 맵 입력
				}
			}

			if (K==1 || test()) {
				sb.append(0).append("\n");
			} else {
				comb(0, 0);
				sb.append(min).append("\n");
			}

			
		}
		System.out.println(sb.toString());
	}

	private static void comb(int idx, int cnt) {

		if (cnt >= min) {
			return;
		}
		if (idx == D) {

			if (test()) {
				min = Math.min(min, cnt);
			}

			return;
		}

		// 모든 행에 대해서 진행

		// 아무것도 처리 안하기
		comb(idx + 1, cnt);

		// A넣기
		for (int i = 0; i < W; i++) {
			tempMap[idx][i] = 0;
		}
		comb(idx + 1, cnt + 1);

		// B넣기
		for (int i = 0; i < W; i++) {
			tempMap[idx][i] = 1;
		}
		comb(idx + 1, cnt + 1);

		// 원상복구
		for (int i = 0; i < W; i++) {
			tempMap[idx][i] = map[idx][i];
		}

	}

	private static boolean test() {

		for (int j = 0; j < W; j++) {
			int count = 0;
			int check = tempMap[0][j];
			boolean flag = false;
			for (int i = 0; i < D; i++) {
				int value = tempMap[i][j];
				if (check == value) {
					count++;
					if (count == 3) {
						flag = true;
						break;
					}
				} else {
					check = value;
					count = 1;
				}
			}
			if (!flag) {
				return false;
			}
		}
		return true;
	}
}
