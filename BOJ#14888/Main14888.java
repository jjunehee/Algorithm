
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연산자 끼워넣기
public class Main14888 {
	static int[] Operator = new int[4];
	static int[] num;
	static int N;
	static int[] pick;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			Operator[i] = Integer.parseInt(st.nextToken());
		}

		pick = new int[N - 1];

		perm(0);
		System.out.println(max);
		System.out.println(min);
	}

	private static void perm(int cnt) {
		if (cnt == N - 1) {
			calculate();
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (Operator[i] == 0) {
				continue;
			}
			Operator[i]--;
			pick[cnt] = i;
			perm(cnt + 1);
			Operator[i]++;
		}
		
	}

	private static void calculate() {
		int res = num[0];
		for (int i = 0; i < N - 1; i++) {
			switch (pick[i]) {
			case 0:
				res += num[i + 1];
				break;
			case 1:
				res -= num[i + 1];
				break;
			case 2:
				res *= num[i + 1];
				break;
			case 3:
				res /= num[i + 1];
				break;
			}
		}
		min = Math.min(min, res);
		max = Math.max(max, res);
	}
}
