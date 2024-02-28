import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 계란으로 계란치
public class BOJ16987 {

	static int N, S, W;

	static Egg[] eggArray;

	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		eggArray = new Egg[N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			eggArray[i] = new Egg(S, W);
		}

		dfs(1, 0);
		System.out.println(max);
	}

	public static void dfs(int idx, int cnt) {

		if (idx == N + 1) {
			max = Math.max(max, cnt);
			return;
		}

		if (cnt + (N - idx + 1) * 2 < max) {
			return;
		}

		if (eggArray[idx].s <= 0 || cnt == N - 1) {
			dfs(idx + 1, cnt);
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (idx != i && eggArray[i].s > 0) {
				int count = 0;
				eggArray[i].s -= eggArray[idx].w;
				if (eggArray[i].s <= 0) {
					count++;
				}
				eggArray[idx].s -= eggArray[i].w;
				if (eggArray[idx].s <= 0) {
					count++;
				}
				dfs(idx + 1, cnt + count);
				eggArray[i].s += eggArray[idx].w;
				eggArray[idx].s += eggArray[i].w;
			}
		}

	}

	public static class Egg {
		int s, w;

		public Egg(int s, int w) {
			this.s = s;
			this.w = w;
		}
	}
}
