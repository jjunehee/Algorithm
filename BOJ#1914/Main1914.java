import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main1914 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static BigInteger ans = new BigInteger("2");

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		ans = ans.pow(N).subtract(new BigInteger("1"));
		System.out.println(ans);
		if (N <= 20) {
			hanoi(N, 1, 2, 3);
			System.out.println(sb.toString());
		} else {
			return;
		}

	}

	private static void hanoi(int n, int from, int tmp, int to) {

		if (n == 1) {
			sb.append(from + " " + to).append("\n");
			return;
		}

		// n-1 from to tmp
		hanoi(n - 1, from, to, tmp);

		// 가장 큰 것 from -> to
		sb.append(from + " " + to).append("\n");

		// tmp -> to
		hanoi(n - 1, tmp, from, to);

	}

}
