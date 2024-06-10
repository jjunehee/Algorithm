
import java.io.*;
import java.util.*;

public class SFT136337 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] numArray = new int[N + 1];
		int[] prefixSum = new int[N + 1];
		prefixSum[0] = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numArray[i] = Integer.parseInt(st.nextToken());
			prefixSum[i] = numArray[i] + prefixSum[i - 1];
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			String avg = String.format("%.2f", (double) (prefixSum[B] - prefixSum[A - 1]) / (B - A + 1));
			sb.append(avg).append("\n");
		}

		System.out.println(sb.toString());

	}
}
