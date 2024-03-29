import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2805_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] trees = new int[N];
		st = new StringTokenizer(br.readLine());
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			if(max < trees[i]) {
				max = trees[i];
			}
		}

		int min = 0;
		int mid = 0;
		while(min <= max) {
			
			mid = (min + max) / 2;
			
			long sum = 0;
			for(int num : trees) {
				
				if(mid < num) {
					sum += (num - mid);
				}
			}
			
			if(sum < M) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}
			
		}
		
		System.out.println(min - 1);


	}
}
