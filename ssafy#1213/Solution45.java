import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution45 {
	static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new int[10];
		for (int t = 0; t < 10; t++) {
			int count = 0;
			int test_case = Integer.parseInt(br.readLine());
			String check = br.readLine();
			String str = br.readLine();

			for (int i = 0; i < str.length() - check.length()+1; i++) {
				if (check.equals(str.substring(i, i + check.length()))) {
					count++;
				}
			}
			answer[t] = count;
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}
}
