import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2003_2 {
	static int[] pick;
	static int N, M;
	static int[] numArray;
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numArray = new int[N + 1];
		numArray[0] = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numArray[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		int start = 1;
		int end = 1;
		int sum = 0;
		
		while(start <= N) {
			if(sum > M || end == N+1) {
				sum -= numArray[start];
				start++;
			} else {
				sum += numArray[end];
				end++;
			}
			
			if(sum==M) cnt++;
		}
		
		System.out.println(cnt);
		
		
		
	}

}
