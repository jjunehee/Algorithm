import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution34 {
	static int[] nums;
	static int N, K;
	static int count;
	static int[] answer;

	public static void main(StringProblem[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			count = 0;
			dfs(0, 0);
			answer[t] = count;
		}

		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}

	public static void dfs(int index, int sum) {

		if (sum == K) {
			count++;
		}

		for (int i = index; i < N; i++) {
			dfs(i + 1, sum + nums[i]);
		}

	}
}
