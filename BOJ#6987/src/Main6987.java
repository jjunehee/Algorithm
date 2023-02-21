import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main6987 {
	static int[] report;
	static int[] pick;
	static boolean flag;
	static int count;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuffer sb = new StringBuffer();

		for (int t = 1; t <= 1; t++) {
			report = new int[19];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 18; i++) {
				report[i] = Integer.parseInt(st.nextToken());
			}

			for (int country = 0; country < 6; country++) {

				pick = new int[4];
				flag = false;
				comb(0, country);
				if (!flag) {
					sb.append("0 ");
					break;
				}
			}
			if (flag) {
				sb.append("1 ");
			}

		}
//		System.out.println(sb.toString());

	}

	private static void comb(int cnt, int country) {
		if (cnt == 5) {
			for (int i = 1; i <= 3; i++) {
				System.out.print(pick[i] + " ");
			}
			flag = true;
			return;
		}

		// 가지치기를 하면서 원하는 재귀로만 뻗어가자.
		// 근데 가지치기해서 아무리 잘 뻗어나간다고 해도
		// 나라별 경기 수 5만 맞춰진거고,
		// 승,무,패가 알맞게 분배된것을 판단할 수가 없다.

		if (pick[1] < report[3 * country + 1]) {
			if (minusLose())
				comb(cnt + 1, country);
		} else if (pick[2] < report[3 * country + 2]) {
			if (minusDraw())
				comb(cnt + 1, country);
		} else if (pick[3] < report[3 * country + 3]) {
			if (minusWin())
				comb(cnt + 1, country);
		}

	}

	private static boolean minusWin() {

		for (int i = 1; i <= 6; i++) {
		}
		return false;
	}

	private static boolean minusLose() {

		return false;
	}

	private static boolean minusDraw() {

		return false;
	}
}
