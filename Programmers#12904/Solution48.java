import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution48 {

	// get longest pelindrome from input string
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int answer = 0;
		boolean check = false;
		char[] c = str.toCharArray();

		for (int len = str.length(); len > 1; len--) {
			if(check) {
				break;
			}
			for (int start = 0; start + len <= str.length(); start++) {
				check = true;
				for (int i = 0; i < len / 2; i++) {
					if (c[start + i] != c[start + len - i - 1]) {
						check = false;
						break;
					}
				}
				if (check) {
					answer = len;
					break;
				}
			}
		}
		if(!check) {
			answer = 1;
		}
		System.out.println(answer);
	}
}