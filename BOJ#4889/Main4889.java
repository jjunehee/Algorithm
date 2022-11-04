import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main4889 {
	public static void main(StringProblem[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int test_case = 1;
		while (true) {
			StringProblem str = br.readLine();
			if (str.contains("-")) {
				break;
			}
			Stack<Character> stack = new Stack<>();
			int cnt = 0;
			for (int i = 0; i < str.length(); i++) {
				if(str.charAt(i) == '{') {
					stack.add(str.charAt(i));
				} else if (str.charAt(i) == '}') {
					if(stack.isEmpty()) {
						stack.add('{');
						cnt++;
					}
					else {
						stack.pop();
					}
				}	
			}
			sb.append(test_case + ". " + (cnt + stack.size()/2) + "\n");
			test_case++;
		}
		System.out.println(sb.toString());
	}
}
