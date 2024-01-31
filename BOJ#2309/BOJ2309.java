import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2309 {

	static int[] fuckingLoser;
	static int[] pick;
	static boolean flag = false;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		fuckingLoser = new int[9];
		for (int i = 0; i < 9; i++) {
			fuckingLoser[i] = Integer.parseInt(br.readLine());
		}

		pick = new int[7];
		comb(0, 0);
		System.out.println(sb.toString());
	}

	public static void comb(int idx, int cnt) {

		if (flag) {
			return;
		}
		if (cnt == 7) {

			int sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += pick[i];
			}
			if (sum == 100) {
				Arrays.sort(pick);
				for (int num : pick) {
					sb.append(num).append("\n");
				}
				flag = true;
			}
			return;
		}

		for (int i = idx; i < 9; i++) {
			pick[cnt] = fuckingLoser[i];
			comb(i + 1, cnt + 1);
		}
	}
}
