import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1339 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] info = new int[26];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();

			int size = str.length();

			int base = (int) Math.pow(10, size - 1);

			for (int t = 0; t < size; t++) {
				info[str.charAt(t) - 'A'] += base;
				base /= 10;
			}
		}

		Arrays.sort(info);

		int ans = 0;
		for (int i = 25; i >= 17; i--) {
			ans += info[i] * (i - 16);
		}
		System.out.println(ans);
	}

}
