import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2493 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			stack.push(Integer.parseInt(st.nextToken()));
		}
		int[] result = new int[N];
		int idx = result.length - 1;
		while (!stack.isEmpty()) {
			int height = stack.pop();
			int h = stack.peek();
			if (h > height) {
			}
		}

	}

}
