import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution51 {
	static int[] A = new int[10];
	static int[] B = new int[10];
	static boolean[] visited = new boolean[19];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 9; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				visited[i] = true;
			}

			for (int i = 1; i <= 18; i++) {
				if (!visited[i]) {
					B[i] = i;
				}
			}
			dfs(0, A, B);

		}

	}

	public static void dfs(int count, int[] A, int[] B) {
		if (count == 9) {

			return;
		}
		for (int i = 1; i <= 9; i++) {
			
		}
	}
}
