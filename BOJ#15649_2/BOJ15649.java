import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649 {

	static int N,M;
	static int[] pick;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pick = new int[M+1];
		visited = new boolean[N+1];
		permu(0);
		
		System.out.println(sb.toString());
		
		
	}
	
	static void permu(int cnt) {
		if(cnt == M) {
			for(int i=0; i<=M-1; i++) {
				sb.append(pick[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		
		for(int i=1; i<=N; i++) {
			if(visited[i]) {
				continue;
			}
			
			pick[cnt] = i;
			visited[i] = true;
			permu(cnt+1);
			visited[i] = false;
		}
	}
	
	
	
}