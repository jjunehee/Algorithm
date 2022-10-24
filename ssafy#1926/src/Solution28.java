package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution28 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int num = 1; num <= N; num++) {
			int count = 0;
			String[] st = Integer.toString(num).split("");
			for(String s : st) {
				if(s.equals("3") || s.equals("6") || s.equals("9")) {
					count++;
				}
			}
			if(count==0) {
				System.out.print(num + " ");
			}else {
				for(int i=0; i<count; i++) {
					System.out.print("-");
				}
				System.out.print(" ");
			}
		}
	}
}
