import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2493Stack {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Stack<Tower> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			Tower now = new Tower(i, Integer.parseInt(st.nextToken()));

			while (!stack.empty() && stack.peek().height < now.height) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				sb.append("0 ");
			} else {
				sb.append(stack.peek().num + " ");
			}
			stack.push(now);
		}
		System.out.println(sb.toString());

	}

	public static class Tower {
		int num, height;

		public Tower(int num, int height) {
			this.num = num;
			this.height = height;
		}
	}
}
