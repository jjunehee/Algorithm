package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution33 {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 0; t < 10; t++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[8][8];

			for (int i = 0; i < 8; i++) {
				String st = br.readLine();
				for (int j = 0; j < 8; j++) {
					map[i][j] = st.charAt(j);
				}
			}
			
			
			
		}
	}
}
