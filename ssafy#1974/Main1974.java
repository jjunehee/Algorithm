import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1974 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		int[][] map = new int[9][9];

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= T; t++) { // 맵 입력
			sb.append("#" + t + " ");
			for (int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// row check
			boolean isOk = true;
			for (int i = 0; i < 9; i++) {
				boolean[] check = new boolean[10];
				for (int j = 0; j < 9; j++) {

					if (check[map[i][j]]) {
						isOk = false;
						break;
					}
					check[map[i][j]] = true;
				}
			}

			for (int j = 0; j < 9; j++) {
				boolean[] check = new boolean[10];
				for (int i = 0; i < 9; i++) {

					if (check[map[i][j]]) {
						isOk = false;
						break;
					}
					check[map[i][j]] = true;
				}
			}

			for (int i = 0; i < 9; i += 3) {
				for (int j = 0; j < 9; j += 3) {
					if(boxCheck(i, j, map) == false) {
						isOk = false;
					}
				}
			}
			sb.append(isOk== true ? 1 : 0).append("\n");
		}
		System.out.println(sb.toString());

		
	}

	static boolean boxCheck(int s1, int s2, int[][] map) {
		boolean[] check = new boolean[10];

		for (int i = s1; i < s1 + 3; i++) {
			for (int j = s2; j < s2 + 3; j++) {
				if (check[map[i][j]]) {
					return false;
				}
				check[map[i][j]] = true;
			}
		}
		return true;
	}
}
