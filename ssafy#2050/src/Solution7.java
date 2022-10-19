package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution7 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();

		for (int i = 0; i < st.length(); i++) {
			System.out.print((int) st.charAt(i) - 64 + " ");
		}
	}
}
