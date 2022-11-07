import java.io.*;
import java.util.*;

public class Solution43 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] answer = new int[10];
		for (int t = 0; t < 10; t++) {
			int test_case = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			answer[t] = recursion(N,M);
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}

	public static int recursion(int N, int M) {
		if (M == 0) {
			return 1;
		}

		return N*recursion(N, M-1);

	}
}
