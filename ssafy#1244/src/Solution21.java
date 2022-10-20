package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution21 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String num = st.nextToken();
			int count = Integer.parseInt(st.nextToken());

			for (int j = 0; j < count; j++) {
				int max = 0;
				for (int k = 0; k < num.length(); k++) {
					max = Math.max(max, num.charAt(k));
				}
				num.replace(j, max);
			}
		}
	}
}
