import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1074_re {
	static int N;
	static int r;
	static int c;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int size = (int) Math.pow(2, N);

		System.out.println(divide(0, 0, size));
	}

	private static int divide(int x, int y, int size) {
		if (size == 1) {
			return 0;
		}

		int half = size / 2;
		if (r < x + half && c < y + half) {
			return divide(x, y, half);
		} else if (r < x + half && c >= y + half) {
			return divide(x, y + half, half) + (int) Math.pow(half, 2);
		} else if (r >= x + half && c < y + half) {
			return divide(x + half, y, half) + (int) Math.pow(half, 2) * 2;
		} else {
			return divide(x + half, y + half, half) + (int) Math.pow(half, 2) * 3;
		}
	}
}
