import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15655 {

	static int N, M;
	static int[] numArray;
	static int[] pick;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numArray = new int[N];
		pick = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numArray[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numArray);
		
		permu(0,0);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		
	}
	
	public static void permu(int idx, int cnt) {
		
		if(cnt == M) {
			for(int n : pick) {
				sb.append(n + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=idx; i<N; i++) {
			
			pick[cnt] = numArray[i];
			permu(i+1, cnt+1);
		}
	}
	
}
