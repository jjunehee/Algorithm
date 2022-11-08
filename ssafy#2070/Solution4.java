

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution4 {

	static char[] list;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new char[N + 1];
		for (int test_case = 1; test_case <= N; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			char str;
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			if (L > R) {
				str = '>';
			} else if (L < R) {
				str = '<';
			} else {
				str = '=';
			}
			list[test_case] = str;

		}

		for (

				int i = 1; i <= N; i++) {
			System.out.println("#" + i + " " + list[i]);
		}

	}
}
