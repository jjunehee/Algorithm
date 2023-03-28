import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1463 {

	private static int[] value;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		value = new int[1000001];
		Arrays.fill(value, 100);
		value[1] = 0;
		value[2] = 1;
		value[3] = 1;
		Solution(n);
		System.out.println(value[n]);

	}

	private static void Solution(int n) {

		for (int i = 4; i <= n; i++) {

			if (i % 3 == 0) {
				value[i] = value[i / 3] + 1;
			}
			
			if (i % 2 == 0) {
				value[i] = Math.min(value[i / 2] + 1, value[i]);
			}
			
			value[i] = Math.min(value[i - 1] + 1, value[i]);

		}

	}

}
