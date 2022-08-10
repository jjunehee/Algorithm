package src;

import java.io.*;
import java.util.*;

public class Main14888 {

	static int[] numbers;
	static int[] operators;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i: numbers) {
			System.out.print(i + " ");
		}
		System.out.println();
		for(int i: operators) {
			System.out.print(i + " ");
		}
		
		solution();
	}

	private static void solution() {
		// TODO Auto-generated method stub
		
	}
}
