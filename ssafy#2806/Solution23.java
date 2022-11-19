
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution23 {
	static int[] map;
	static int N;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			map = new int[N];
			count = 0;
			dfs(0);
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int depth) {
		if (depth == N) {
			count++;
			return;
		}

		for (int i = 0; i < N; i++) {
			map[depth] = i;

			if (possible(depth)) {
				dfs(depth + 1);
			}
		}
	}

	public static boolean possible(int depth) {
		for (int i = 0; i < depth; i++) {
			if (map[i] == map[depth]) { // row check
				return false;
			} else if (Math.abs(depth - i) == Math.abs(map[depth] - map[i])) {
				return false;
			}
		}
		return true;
	}
}