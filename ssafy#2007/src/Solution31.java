package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution31 {
	static int[] answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int i = 0; i < T; i++) {
			String str = br.readLine();
			for (int length = 1; length <= 10; length++) {
				String a = str.substring(0,length);
				String b = str.substring(length,length*2);
				if(a.equals(b)) {
					answer[i] = length;
					break;
				}
				
			}
		}
		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}
}
