
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2007 {
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new int[N + 10];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());

			int k=1;
			while (num*k <= N) {
				if(arr[num*k]==0) {
					arr[num*k] = 1;
				}else if(arr[num*k]==1) {
					arr[num*k] = 0;
				}
				k++;
			}
		}
		
		for (int i = 1; i <= 10; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
