import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {

	static int N, S;
	static int result;
	static int[] numList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		numList = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numList[i] = Integer.parseInt(st.nextToken());
		}

		comb(0,0, 0);
		
		if(S==0) {
			System.out.println(result-1);
		} else {
			System.out.println(result);
		}
	}

	public static void comb(int sum,int idx, int cnt) {

		if (cnt == N) {
			if (sum == S) {
				result++;
			}
			return;
		}

		if (idx == N) {
			return;
		}

		comb(sum + numList[idx],idx + 1, cnt + 1);
		comb(sum, idx + 1, cnt + 1);
	}
}
