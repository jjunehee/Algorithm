import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution39 {
	static String[] answer;
	public static void main(StringProblem[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new String[10];
		for (int t = 0; t < 10; t++) {
			Queue<Integer> q = new LinkedList<>();
			int n = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			int first;
			int value = 1;
			while (true) {
				first = q.poll();

				first -= value;
				if (first <= 0) {
					q.add(0);
					break;
				}
				q.add(first);
				value = (value % 5)+1;
			}

			String str = "#" + (t + 1) + " ";
			while (!q.isEmpty()) {
				str += String.valueOf(q.poll()) + " ";
			}

			answer[t] = str;

		}

		for (int i = 0; i < 10; i++) {
			System.out.println(answer[i]);
		}
	}
}
