package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution19 {
	static String[] answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		answer = new String[n+1];

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int meter = Integer.parseInt(st.nextToken());
			if(meter%2 ==0) {
				answer[i] = "Alice";
			} else {
				answer[i] = "Bob";
			}
		}
		
		for(int i=1; i<=n; i++) {
			System.out.println("#" + i + " " + answer[i]);
		}
		
	}
}
