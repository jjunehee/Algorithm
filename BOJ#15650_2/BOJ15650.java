import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15650 {

	static int N,M;
	static int[] pick;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pick = new int[M+1];
		permu(1,0);
		
		System.out.println(sb.toString());
		
		
	}
	
	static void permu(int num, int cnt) {
		if(cnt == M) {
			for(int i=0; i<=M-1; i++) {
				sb.append(pick[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		
		for(int i=num; i<=N; i++) {
			pick[cnt] = i;
			permu(i+1, cnt+1);
		}
	}
	
	
	
}
