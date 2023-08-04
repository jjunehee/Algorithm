import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AutoEver1 { // testCase - Input adbfdf a####f, Output : YES
	static String board;
	static String pattern;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = br.readLine();
		pattern = br.readLine();
		boolean flag = false;
		for (int i = 0; i <= board.length() - pattern.length(); i++) {
			if (search(i))
				flag = true;
		}

		if (flag) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

	public static boolean isVowel(char x) {
		return x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u';
	}

	public static boolean search(int index) {
		for (int i = 0, j = index; i < pattern.length(); i++, j++) {
			if (pattern.charAt(i) == '@') { // @�̸�~
				if (!isVowel(board.charAt(j)))
					return false;
			} else if (pattern.charAt(i) == '#') { // #�̸�~
				if (isVowel(board.charAt(j)))
					return false;
			} else if (pattern.charAt(i) != board.charAt(j)) {
				return false;
			}
		}
		return true;
	}
}