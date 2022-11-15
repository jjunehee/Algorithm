import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution54 {
	static boolean check[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			check = new boolean[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < K; n++) {
				check[Integer.parseInt(st.nextToken())] = true;
			}

			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int n = 1; n <= N; n++) {
				if(!check[n]) {
					pq.add(n);
				}
			}
			
			for(Integer answer: pq) {
				sb.append(answer + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}
}
