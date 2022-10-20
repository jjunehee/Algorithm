package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution18 {
	static String[] str;
	static int[] list;
	static boolean signal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		list = new int[n + 1];
		str = new String[n+1];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			str[i] = st.nextToken();
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < str[i].length(); j++) {
				if (str[i].charAt(j) == (char)('a' + j)) {
					list[i] += 1;
					continue;
				}
				break;
			}
		}
		for (

				int i = 1; i <= n; i++) {
			System.out.println("#" + i + " " + list[i]);
		}

	}

}
