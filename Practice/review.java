import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class review {

	static String board;
	static String pattern;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = br.readLine(); // abbbcc
		pattern = br.readLine(); // ab#@c

		boolean flag = false;
		for (int i = 0; i <= board.length() - pattern.length(); i++) {
			if (search(i)) {
				flag = true;
				break;
			} else {
				continue;
			}
		}

		if (flag) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

	public static boolean search(int index) {
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) == '@') {
				if (isVowel(board.charAt(i + index))) {
					continue;
				} else {
					return false;
				}
			} else if (pattern.charAt(i) == '#') {
				if (!isVowel(board.charAt(i+ index))) {
					continue;
				} else {
					return false;
				}
			} else {
				if (pattern.charAt(i) == board.charAt(i+ index)) {
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isVowel(char check) {
		return check == 'a' || check == 'e' || check == 'i' || check == 'o' || check == 'u';
	}

}
