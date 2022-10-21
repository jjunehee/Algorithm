package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution14 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		for (int j = 1; j <= 4; j++) {
			switch(j) {
			case 1:
				System.out.println(A+B);
				break;
			case 2:
				System.out.println(A-B);
				break;
			case 3:
				System.out.println(A*B);
				break;
			case 4:
				System.out.println(A/B);
				break;
			}
		}
	}
}
