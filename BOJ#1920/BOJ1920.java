import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1920 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] A = new int[N];
		
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		Arrays.sort(A);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			int check = Integer.parseInt(st.nextToken());
			if(Arrays.binarySearch(A, check) >= 0) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		
		System.out.println(sb.toString());
		
	}
}
