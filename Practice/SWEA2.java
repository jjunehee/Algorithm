import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2 {

	static int[] exceptMaxValue;
	static int n;
	static int[] pick;
	static int[][] person;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			n = Integer.parseInt(br.readLine());

			exceptMaxValue = new int[n];
			StringTokenizer st;
			int a, b, c;
			int sum = 0;
			person = new int[n][3];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				person[i][0] = a;
				person[i][1] = b;
				person[i][2] = c;
				exceptMaxValue[i] = Math.max(Math.max(a, b), c);
				sum += a + b + c;
			}

			if (n < 3) {
				sb.append(-1).append("\n");
				continue;
			}

			max = Integer.MIN_VALUE;
			pick = new int[3];
			comb(0, 0);

			sb.append(sum - max).append("\n");
		}

		System.out.println(sb.toString());
	}

	public static void comb(int idx, int cnt) {
		if (cnt == 3) {

			boolean[] visited = new boolean[n];
			for (int num : pick) {
				visited[num] = true;
			}
			
			int restSum = 0;
			for (int i = 0; i < n; i++) {
				if(!visited[i]) {
					restSum += exceptMaxValue[i];
				}
			}
			
			int a = 0;
			a += person[pick[0]][0] + person[pick[1]][1] + person[pick[2]][2];

			int b = 0;
			b += person[pick[0]][0] + person[pick[1]][2] + person[pick[2]][1];

			int c = 0;
			c += person[pick[0]][1] + person[pick[1]][0] + person[pick[2]][2];

			int d = 0;
			d += person[pick[0]][1] + person[pick[1]][2] + person[pick[2]][0];

			int e = 0;
			e += person[pick[0]][2] + person[pick[1]][0] + person[pick[2]][1];

			int f = 0;
			f += person[pick[0]][2] + person[pick[1]][1] + person[pick[2]][0];

			max = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(a, b), c), d), e), f) + restSum, max);
			
			return;
		}

		for (int i = idx; i < n; i++) {
			pick[cnt] = i;
			comb(i + 1, cnt + 1);
		}
	}

}