import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759 {

	static char[] alphabet;
	static int L, C;
	static char[] pick;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		alphabet = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(alphabet);

		pick = new char[L];
		comb(0, 0);
		System.out.println(sb.toString());
	}

	public static void comb(int idx, int cnt) {

		if (cnt == L) {
			if (checkVowel(pick) && checkConsonant(pick)) {
				for (char c : pick) {
					sb.append(c);
				}
				sb.append("\n");
			}
			return;
		}

		for (int i = idx; i < C; i++) {
			pick[cnt] = alphabet[i];
			comb(i + 1, cnt + 1);
		}
	}

	public static boolean checkVowel(char[] pick) {

		for (char c : pick) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				return true;
			}
		}

		return false;
	}

	public static boolean checkConsonant(char[] pick) {
		int cnt = 0;

		for (char c : pick) {
			if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
				cnt++;
			}
		}
		
		if(cnt>=2) {
			return true;
		} else {
			return false;
		}
	}
}
