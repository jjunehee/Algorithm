import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea4012 {
	static int[][] S;
	static int[] Aingredient, Bingredient;
	static boolean[] Ingredient;
	static int Asynergy, Bsynergy;
	static int min;
	static int N;
	static int[] a, b;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			N = Integer.parseInt(br.readLine());
			S = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Aingredient = new int[N / 2];
			Bingredient = new int[N / 2];
			Ingredient = new boolean[N + 1];
			min = Integer.MAX_VALUE;

			comb(0, 1);

			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void comb(int cnt, int idx) {
		if (cnt == N / 2) {
			cookAndCaculate();
			return;
		}

		if (idx == N + 1) {
			return;
		}

		for (int i = idx; i <= N; i++) {
			Aingredient[cnt] = i;
			Ingredient[i] = true;
			comb(cnt + 1, i + 1);
			Ingredient[i] = false;
		}
	}

	private static void cookAndCaculate() {
		int index = 0;
		for (int i = 1; i <= N; i++) {
			if (Ingredient[i])
				continue;
			Bingredient[index++] = i;
		}

		Asynergy = 0;
		Bsynergy = 0;

		a = new int[2];
		Acomb(0, 0);
		b = new int[2];
		Bcomb(0, 0);

		min = Math.min(min, Math.abs(Asynergy - Bsynergy));

	}

	private static void Acomb(int cnt, int idx) {
		if (cnt == 2) {
			Asynergy += (S[a[0]][a[1]] + S[a[1]][a[0]]);
			return;
		}

		if (idx == N / 2) {
			return;
		}

		for (int i = idx; i < N / 2; i++) {
			a[cnt] = Aingredient[i];
			Acomb(cnt + 1, i + 1);
		}
	}

	private static void Bcomb(int cnt, int idx) {
		if (cnt == 2) {
			Bsynergy += (S[b[0]][b[1]] + S[b[1]][b[0]]);
			return;
		}

		if (idx == N / 2) {
			return;
		}

		for (int i = idx; i < N / 2; i++) {
			b[cnt] = Bingredient[i];
			Bcomb(cnt + 1, i + 1);
		}
	}

}
