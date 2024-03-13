
import java.util.*;
import java.io.*;

public class BOJ15961 {

	static int N, d, k, c;
	static int[] sushi;
	static int[] cnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		sushi = new int[N];
		cnt = new int[d+1];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		cnt[c]++;
		int sum = 1;

		for (int i = 0; i < k; i++) {
			if (cnt[sushi[i]] == 0) {
				sum++;
			}
			cnt[sushi[i]]++;
		}
		
		int max = Integer.MIN_VALUE;
		max = sum;

		for (int s = 0; s < N; s++) {
			cnt[sushi[s]]--;
			if (cnt[sushi[s]] == 0) {
				sum--;
			}
			
			int next = (s + k) % N;
			if(cnt[sushi[next]] == 0) {
				sum++;
			}
			cnt[sushi[next]]++;
			max = Math.max(max, sum);
		}
		
		bw.write(max +"\n");
		bw.flush();
		bw.close();
		br.close();

	}
}