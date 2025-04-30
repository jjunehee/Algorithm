import java.util.*;
import java.io.*;

public class SOFTEER9495 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int popularity = 0;
		boolean isPossible = false;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			isPossible = (Math.abs(P-popularity) <= C);
			if(isPossible) {
				popularity++;
			}
			
		}
		
		System.out.print(popularity);
	}
}
