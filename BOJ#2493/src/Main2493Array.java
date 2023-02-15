import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2493Array {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Tower[] stack = new Tower[N];
		int top = 0;
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			Tower now = new Tower(i, Integer.parseInt(st.nextToken()));

			while (top > 0 && stack[top - 1].height < now.height) {
				top--;
			}

			if (top == 0) {
				sb.append("0 ");
			} else {
				sb.append(stack[top - 1].num + " ");
			}

			stack[top] = now;
			top++;

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
