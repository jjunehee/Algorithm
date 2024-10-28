import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2309_2 {

	static int[] pick = new int[7];
	static int[] heights = new int[10];
	static boolean endFlag = false;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}

		comb(0, 0);

	}

	public static void comb(int idx, int cnt) {

		if (endFlag) {
			return;
		}

		if (cnt == 7) {
			if (calculate()) {
				endFlag = true;
			}
			return;
		}

		for (int i = idx; i < 9; i++) {
			pick[cnt] = heights[i];
			comb(idx + 1, cnt + 1);
		}
	}

	public static boolean calculate() {
		int sum = 0;
		for (int i = 0; i < 7; i++) {
			sum += pick[i];
		}
		if (sum == 100) {
			Arrays.sort(pick);
			for (int i = 0; i < 7; i++) {
				System.out.println(pick[i]);
			}
			return true;
		} else {
			return false;
		}
	}
}
