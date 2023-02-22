import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main6987 {
	static boolean flag;
	static int[][] countries;
	static final int win = 0;
	static int draw = 1;
	static int lose = 2;
	static int[][] copy;
	static int[] pick;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuffer sb = new StringBuffer();

		for (int t = 1; t <= 1; t++) {
			countries = new int[7][3];
			copy = new int[7][3];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 6; i++) {
				countries[i][0] = Integer.parseInt(st.nextToken());
				countries[i][1] = Integer.parseInt(st.nextToken());
				countries[i][2] = Integer.parseInt(st.nextToken());
			}

			pick = new int[3];
			combTeam(0, 0);
			if (check()) {
				sb.append("1 ");
			} else {
				sb.append("0 ");
			}

		}
		System.out.println(sb.toString());

	}

	private static boolean check() {
		for (int i = 1; i <= 6; i++) {
			for (int j = 0; j < 3; j++) {

				if (copy[i][j] != 0) {
					return false;
				}
			}
		}

		return true;
	}

	private static void combTeam(int cnt, int idx) {
		if (cnt == 2) {
			for (int i = 1; i <= 6; i++) {
				copy[i] = countries[i].clone();
			}
			comb(0, 0);
		}

		// 대결할 두팀을 뽑아야 함
		for (int i = idx; i <= 6; i++) {
			pick[cnt] = i;
			combTeam(cnt + 1, i + 1);
		}
	}

	private static void comb(int cnt, int country) {
		if (cnt == 10) {
			return;
		}

		// 1팀이 이기는 경우
		countries[pick[1]][win]--;
		countries[pick[2]][lose]--;
		comb(cnt + 1, country);
		countries[pick[1]][win]++;
		countries[pick[2]][lose]++;

		// 2팀이 이기는 경우
		countries[pick[1]][lose]--;
		countries[pick[2]][win]--;
		comb(cnt + 1, country);
		countries[pick[1]][lose]++;
		countries[pick[2]][win]++;

		// 1,2팀이 무승부 하는 경우
		countries[pick[1]][draw]--;
		countries[pick[2]][draw]--;
		comb(cnt + 1, country);
		countries[pick[1]][draw]++;
		countries[pick[2]][draw]++;

	}
}
