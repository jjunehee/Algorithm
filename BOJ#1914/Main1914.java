import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1914 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		if (N <= 20) {
			hanoi(N, 1, 2, 3);
			System.out.println(cnt);
			System.out.println(sb.toString());
		} else {
			hanoi(N, 1, 2, 3);
			System.out.println(cnt);
		}
		
	}

	private static void hanoi(int n, int from, int tmp, int to) {
		cnt++;
		if (n == 1) {
			sb.append(from + " " + to).append("\n");
			return;
		}

		// n-1번째 블록을 tmp
		hanoi(n - 1, from, to, tmp);
		// from -> to
		sb.append(from + " " + to).append("\n");
		// tmp -> to
		hanoi(n - 1, tmp, from, to);
	}
}
