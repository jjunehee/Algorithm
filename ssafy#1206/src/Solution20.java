package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution20 {
	static int[] map;
	static int[] answer = new int[11];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int Test_case = 1; Test_case <= 10; Test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			map = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			int max = 0;
			for (int i = 2; i < n - 2; i++) {
				for (int len = 1; len <= 2; len++) { // ¿ÞÂÊ
					max = Math.max(max, map[i - len]);
				}
				for (int len = 1; len <= 2; len++) { // ¿À¸¥ÂÊ
					max = Math.max(max, map[i + len]);
				}
				if (map[i] > max) {
					answer[Test_case] += map[i]-max;
				}

			}
		}
		
		for(int i=1; i<=10; i++) {
			System.out.println("#" + i + " " + answer[i]);
		}
		
		

	}
}
