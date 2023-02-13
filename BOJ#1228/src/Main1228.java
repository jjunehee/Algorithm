import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main1228 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			sb.append("#" + t + " ");
			int N = Integer.parseInt(br.readLine());
			LinkedList<Integer> list = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}

			int n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				String alpha = st.nextToken();
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				for (int j = 0; j < y; j++) {
					list.add(x++, Integer.parseInt(st.nextToken()));
				}
			}

			for (int i = 0; i < 10; i++) {
				sb.append(list.get(i) + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
