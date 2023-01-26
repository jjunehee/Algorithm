import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		final String A = "000000";
		final String B = "001111";
		final String C = "010011";
		final String D = "011100";
		final String E = "100110";
		final String F = "101001";
		final String G = "110101";
		final String H = "111010";

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		String str = br.readLine();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i += 6) {
			String s = str.substring(i, i + 6);

			if (s.equals(A) || check(s, A)) {
				sb.append("A");
			} else if (s.equals(B) || check(s, B)) {
				sb.append("B");
			} else if (s.equals(C) || check(s, C)) {
				sb.append("C");
			} else if (s.equals(D) || check(s, D)) {
				sb.append("D");
			} else if (s.equals(E) || check(s, E)) {
				sb.append("E");
			} else if (s.equals(F) || check(s, F)) {
				sb.append("F");
			} else if (s.equals(G) || check(s, G)) {
				sb.append("G");
			} else if (s.equals(H) || check(s, H)) {
				sb.append("H");
			} else {
				sb = new StringBuffer();
				sb.append(i/6+1);
				break;
			}
		}
		System.out.println(sb.toString());

	}

	private static boolean check(String s, String A) {

		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != A.charAt(i)) {
				cnt++;
			}
		}
		if(cnt==1) {
			return true;
		}

		return false;
	}

}