import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main6987 {
	static int[][] countries;
	static int[][] input;
	static final int win = 0;
	static int draw = 1;
	static int lose = 2;
	static StringBuffer sb = new StringBuffer();
	static int[] teamA = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5 };
	static int[] teamB = { 2, 3, 4, 5, 6, 3, 4, 5, 6, 4, 5, 6, 5, 6, 6 };
	static int ret;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 4; t++) {
			countries = new int[7][3];
			input = new int[7][3];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 6; i++) {
				countries[i][0] = Integer.parseInt(st.nextToken());
				countries[i][1] = Integer.parseInt(st.nextToken());
				countries[i][2] = Integer.parseInt(st.nextToken());
			}

			ret = 0;
			comb(0);
			sb.append(ret + " ");
		}
		System.out.println(sb.toString());

	}

	private static boolean check() {
		for (int i = 1; i <= 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (input[i][j] != countries[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	private static void comb(int cnt) {
		if (cnt == 15) {
			if (check()) {
				ret = 1;
			}
			return;
		}

		int A = teamA[cnt];
		int B = teamB[cnt];

		// 1팀이 이기는 경우
		if (input[A][win] < countries[A][win] && input[B][lose] < countries[B][lose]) {
			input[A][win]++;
			input[B][lose]++;
			comb(cnt + 1);
			input[A][win]--;
			input[B][lose]--;
		}

		// 2팀이 이기는 경우
		if (input[A][lose] < countries[A][lose] && input[B][win] < countries[B][win]) {
			input[A][lose]++;
			input[B][win]++;
			comb(cnt + 1);
			input[A][lose]--;
			input[B][win]--;
		}

		// 1,2팀이 무승부 하는 경우
		if (input[A][draw] < countries[A][draw] && input[B][draw] < countries[B][draw]) {
			input[A][draw]++;
			input[B][draw]++;
			comb(cnt + 1);
			input[A][draw]--;
			input[B][draw]--;
		}

	}
}
