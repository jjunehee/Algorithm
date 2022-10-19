package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution3 {

	static int[] list;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new int[N+1];
		for (int test_case = 1; test_case <= N; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < 10; i++) {
				list[test_case] += Integer.parseInt(st.nextToken());
				
			}
			
		}
		

		for (int i = 1; i <= N; i++) {
			System.out.println("#" + i + " " + Math.round(list[i]/10.0));
		}

	}
}
