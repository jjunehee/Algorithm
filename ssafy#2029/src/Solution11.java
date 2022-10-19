package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution11 {
	static String[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new String[N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			list[i] = Integer.toString(num / value) + " " + Integer.toString(num % value);
		}

		for (int i = 1; i <= N; i++) {
			System.out.println("#" + i + " " + list[i]);
		}

	}
}
