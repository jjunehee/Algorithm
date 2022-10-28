import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution36 {
	static int count;
	static int[] answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int i = 0; i < T; i++) {
			String str = br.readLine();
			String[] st = str.split("");

			count = 0;
			for (int index = 0; index < st.length; index++) {
				if (st[index].equals("1")) {
					search(index, st, st.length);
					break;
				}
			}
			answer[i] = count;
		}
		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i+1) + " " + answer[i]);
		}
	}

	public static void search(int i, String[] st, int end) {
		String prev = "0";	
		for (int start = i; start < end; start++) {
			String cur = st[start];
			if (!prev.equals(cur)) {
				count++;
				prev = cur;
			}
		}
	}
}
