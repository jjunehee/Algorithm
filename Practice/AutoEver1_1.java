import java.io.*;

public class AutoEver1_1 {

	static String board;
	static String pattern;

	// input : abcde a#c output : YES
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = br.readLine();
		pattern = br.readLine();

		StringBuilder sb = new StringBuilder();

		boolean isPattern = false;
		for (int i = 0; i <= board.length() - pattern.length(); i++) {
			String subString = board.substring(i, i + pattern.length());
			System.out.println(subString + " " + pattern);
			if (check(subString, pattern)) { // board, subString
				isPattern = true;
				break;
			}
		}

		if (!isPattern) {
			sb.append("No");
		} else {
			sb.append("Yes");
		}

		System.out.println(sb.toString());

	}

	public static boolean check(String board, String pattern) {
		// 수술대에 올라온 길이가 같은 문자열

		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) == '#') { // 자음
				if(isVowel(board.charAt(i))) {
					return false;
				}
			} else if (pattern.charAt(i) == '@') {
				if(!isVowel(board.charAt(i))) {
					return false;
				}
			} else {
				if(pattern.charAt(i) != board.charAt(i)) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isVowel(char check) {

		return 'a' == check || 'e' == check || 'i' == check || 'o' == check || 'u' == check;
	}
}
