import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15654 {

	static int N,M;
	static int[] numArray;
	static int[] pick;
	
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numArray = new int[N];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numArray[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numArray);
		
		pick = new int[M+1];
		permu(0);
		System.out.print(sb.toString());
		
	}
	
	static void permu(int cnt) {
		if(cnt == M) {
			for(int i=0; i<=M-1; i++) {
				sb.append(pick[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]) {
				continue;
			}
			
			visited[i] = true;
			pick[cnt] = numArray[i];
			permu(cnt+1);
			visited[i] = false;
		}
	}
}
