import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AutoEverReview {

	// Input : abcde a###@ Output : true
	// Input : abcde a@ Output : false
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String board = br.readLine();
		String spam = br.readLine();

		boolean flag = false;
		for (int i = 0; i <= board.length() - spam.length(); i++) {
			String check = board.substring(i, i + spam.length());

			if (checkSpam(check, spam)) {
				flag = true;
				break;
			} else {
				continue;
			}
		}

		if (!flag) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
		}

	}

	public static boolean checkSpam(String board, String spam) {
		for (int i = 0; i < spam.length(); i++) {

			char c = spam.charAt(i);

			if (c == '@') {
				if (isVowel(board.charAt(i), i)) {
					continue;
				} else {
					return false;
				}
			} else if (c == '#') {
				if (!isVowel(board.charAt(i), i)) {
					continue;
				} else {
					return false;
				}
			}
			
			if(c == board.charAt(i)) {
				continue;
			} else {
				return false;
			}

		}
		
		return true;
	}

	public static boolean isVowel(char board, int index) {

		return board == 'a' || board == 'e' || board == 'i' || board == 'o' || board == 'u';
	}

}
