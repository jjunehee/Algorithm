import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution57 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			String str = br.readLine();
			int start = 0;
			int end = str.length() - 1;

			boolean isPelindrome = true;
			for (int i = 0; i < str.length() / 2; i++) {
				if (str.charAt(start) != str.charAt(end)) {
					isPelindrome = false;
					break;
				}
				start++;
				end--;
			}
			sb.append(isPelindrome == true ? "1" : "0").append("\n");
		}
		System.out.println(sb);
	}
}
