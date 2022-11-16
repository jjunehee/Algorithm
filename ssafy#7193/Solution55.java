import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution55 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String X = st.nextToken();

			int value = 0;
			for (int i = 0; i < X.length(); i++) {
				value += Integer.parseInt(X.substring(i,i+1));
			}

			sb.append(value % (N - 1)).append("\n");
		}
		System.out.println(sb);
	}
}
