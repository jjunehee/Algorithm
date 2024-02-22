import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1003 {

	static int[][] fibo = new int[41][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		fibo[0][0] = 1;
		fibo[0][1] = 0;

		fibo[1][0] = 0;
		fibo[1][1] = 1;

		for (int i = 2; i <= 40; i++) {
			fibo[i][0] = fibo[i - 1][0] + fibo[i - 2][0];
			fibo[i][1] = fibo[i - 1][1] + fibo[i - 2][1];
		}

		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int num = Integer.parseInt(br.readLine());
			sb.append(fibo[num][0] + " " + fibo[num][1]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
