
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea2112 {
	static int D, W, K;
	static int[][] map;
	static int[][] copy;
	static int min;
	static boolean Flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine(), " ");
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

			copy = new int[D][W];
			for (int i = 0; i < D; i++) {
				copy[i] = map[i].clone();
			}

			if (test()) {
				sb.append("0").append("\n");
				continue;
			}
			
			min = D;
			comb(0, 0);
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());

	}

	private static boolean test() {
		int cnt = 0;
		for (int j = 0; j < W; j++) {
			Flag = true;
			for (int i = 0; i <= D - K; i++) {
				check(i, j);
				if (Flag) {
					break;
				} else {
					continue;
				}
			}

			if (Flag) {
				cnt++;
			}
		}
		if (cnt == W) {
			return true;
		}
		return false;
	}

	private static void check(int i, int j) {

		for (int t = 0; t < K; t++) {
			if (copy[i][j] == copy[i + t][j]) {
				Flag = true;
				continue;
			}
			Flag = false;
			return;
		}

	}

	private static void comb(int cnt, int idx) {

		// 기저 조건 (백트랙킹)
		if (cnt >= min)
			return;

		if (idx == D) {
			if (test())
				min = Math.min(min, cnt);

			return;
		}

		// 아무것도 안넣기
		comb(cnt, idx + 1);

		// A넣기
		for (int i = 0; i < W; i++) {
			copy[idx][i] = 0;
		}
		comb(cnt + 1, idx + 1);

		// B넣기
		for (int i = 0; i < W; i++) {
			copy[idx][i] = 1;
		}
		comb(cnt + 1, idx + 1);

		// 되돌리기
		for (int i = 0; i < W; i++) {
			copy[idx][i] = map[idx][i];
		}
	}

}