import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10448 {
	static boolean Ureka;
	static int[] pick = new int[3];
	static int[] T = new int[50];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		for (int i = 1; i < 50; i++) {
			T[i] = i * (i + 1) / 2;
		}

		StringBuilder sb = new StringBuilder();
		for (int n = 1; n <= N; n++) {
			int num = Integer.parseInt(br.readLine());
			comb(0, 0, num);
			if (Ureka) {
				sb.append("1").append("\n");
			} else {
				sb.append("0").append("\n");
			}
			Ureka = false;
		}

		System.out.print(sb.toString());
	}

	public static void comb(int idx, int cnt, int num) {
		if (Ureka) {
			return;
		}

		if (cnt == 3) {
			if (checkUreka(num)) {
				Ureka = true;
			}
			return;
		}

		for (int i = 1; i < 50; i++) {
			pick[cnt] = T[i];
			comb(idx, cnt + 1, num);
		}
	}

	public static boolean checkUreka(int check) {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += pick[i];
		}
		return check == sum;
	}
}
