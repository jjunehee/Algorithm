import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2798recur2 {
	static int[] cards;
	static int[] result;
	static int N, M;
	static int sum;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cards = new int[N];
		result = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		recur(0, 0, 0);
		System.out.println(max);
	}

	private static void recur(int cnt, int idx, int result) {
		if (cnt == 3) {

			if (result <= M) {
				max = Math.max(max, result);
			}
			return;
		}
		
		if(idx == cards.length) return;
		
		recur(cnt+1, idx+1, result + cards[idx]);
		recur(cnt, idx+1, result);
		

	}
}
