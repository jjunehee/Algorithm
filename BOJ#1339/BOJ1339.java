import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1339 {

	static final int A = 0;
	static final int B = 1;
	static final int C = 2;
	static final int D = 3;
	static final int E = 4;
	static final int F = 5;
	static final int G = 6;
	static final int H = 7;
	static final int I = 8;
	static final int J = 9;
	static int N;
	static String[] pick;
	static boolean[] visited;
	static String[] strList;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		strList = new String[N];
		for (int i = 0; i < N; i++) {
			strList[i] = br.readLine();
		}

		pick = new String[10];
		visited = new boolean[10];
		permu(0);

		System.out.println(max);
	}

	public static void permu(int cnt) {
		if (cnt == 10) {
			int ret = calculate();
			max = Math.max(max, ret);
			return;
		}

		for (int i = 0; i <= 9; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			pick[cnt] = String.valueOf(i);
			permu(cnt + 1);
			visited[i] = false;
		}
	}

	public static int calculate() {

		int sum = 0;
		for (String str : strList) {
			String unionString = "";
			for (int i = 0; i < str.length(); i++) {
				String s = String.valueOf(str.charAt(i));
				unionString += pick[A];
			}
			sum += Integer.parseInt(unionString);
		}

		return sum;
	}
}
