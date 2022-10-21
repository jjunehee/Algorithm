package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution22 {
	static int[] answer = new int[10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int Dump = Integer.parseInt(st.nextToken());
			int[] boxes = new int[100];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < boxes.length; j++) {
				boxes[j] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(boxes);
			for (int d = 0; d < Dump; d++) {
				boxes[0]++;
				boxes[99]--;
				Arrays.sort(boxes);
			}
			answer[i] = boxes[99] - boxes[0];

		}
		for (int i = 0; i < 10; i++) {
			System.out.println("#" + (i+1) + " " + answer[i]);

		}
	}
}