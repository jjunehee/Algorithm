import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution51 {
	static int[] A;
	static int[] B;
	static boolean[] visited = new boolean[19];
	static boolean[] check;
	static int[] result;
	static int Wincount;
	static int Lostcount;
	static String[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new String[T];
		for (int t = 0; t < T; t++) {
			A = new int[9];
			B = new int[9];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				visited[A[i]] = true;
			}
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!visited[i]) {
					B[idx++] = i;
				}
			}
		

			check = new boolean[9];
			result = new int[9];
			Wincount = 0;
			dfs(0);

			answer[t] = String.valueOf(Wincount) + " " + String.valueOf(9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 - Wincount);
		}
		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}

	}

	public static void dfs(int count) {
		if (count == 9) {

			int Ascore = 0;
			int Bscore = 0;

			for (int i = 0; i < 9; i++) {
				if (A[i] > result[i]) {
					Ascore += A[i] + result[i];
				} else {
					Bscore += A[i] + result[i];
				}
			}

			if (Ascore > Bscore) {
				Wincount++;
			}

			return;
		}
		for (int i = 0; i < 9; i++) {
			if (!check[i]) {
				result[count] = B[i];
				check[i] = true;
				dfs(count + 1);
				check[i] = false;
			}
		}
	}
}
