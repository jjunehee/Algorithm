import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution47 {
	static String[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new String[10];
		for (int t = 0; t < 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String str = st.nextToken();

			Stack<Character> stack = new Stack<>();
			for (int i = 0; i < N; i++) {
				char cur = str.charAt(i);
				if (stack.isEmpty() || cur != stack.peek()) {
					stack.push(cur);
				} else {
					stack.pop();
				}
			}
			String result = "";
			while (!stack.isEmpty()) {
				result += stack.pop();
			}
			StringBuilder sb = new StringBuilder(result);
			answer[t] = sb.reverse().toString();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}

}
