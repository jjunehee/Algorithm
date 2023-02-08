import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//·Î¶Ç
public class Main6603 {

	static int k;
	static int[] result = new int[6];
	static int[] S;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k == 0) {
				break;
			}
			S = new int[k];
			for (int i = 0; i < k; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			recur(0, 0);
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	private static void recur(int cnt, int idx) {
		if (cnt == 6) {
			for (int i = 0; i < result.length; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}

		if (idx == S.length) {
			return;
		}

		for (int i = idx; i < k; i++) {
			result[cnt] = S[i];
			recur(cnt + 1, i + 1);
		}
	}
}
