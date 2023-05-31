import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] sinergy;
	static int[] pick;
	static boolean[] used;

	static int[] p;
	static int A, B;
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());

			sinergy = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					sinergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ret = Integer.MAX_VALUE;
			used = new boolean[N];
			pick = new int[N / 2];
			comb(0, 0);

			sb.append(ret).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void comb(int idx, int cnt) {

		if (cnt == N / 2) {
			calculate();
			return;
		}

		for (int i = idx; i < N; i++) {
			pick[cnt] = i;
			used[i] = true;
			comb(i + 1, cnt + 1);
			used[i] = false;
		}

	}

	public static void calculate() {
		p = new int[2];
		A = 0;
		SinergyComb(pick, 0, 0, 'A');

		
		int[] pickB = new int[N/2];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			if (!used[i]) {
				pickB[idx++] = i;
			}
		}

		B = 0;	
		SinergyComb(pickB, 0, 0, 'B');

		ret = Math.min(ret, Math.abs(A - B));
	}

	public static void SinergyComb(int[] pick, int idx, int cnt, char mode) {

		if (cnt == 2) {
			if (mode == 'A') {
				A += sinergy[p[0]][p[1]] + sinergy[p[1]][p[0]];
			} else {
				B += sinergy[p[0]][p[1]] + sinergy[p[1]][p[0]];
			}
			return;
		}

		for (int i = idx; i < N / 2; i++) {
			p[cnt] = pick[i];
			SinergyComb(pick, i + 1, cnt + 1, mode);
		}

	}

}
