package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution25 {
	static int[] info;
	static int max = Integer.MIN_VALUE;
	static Queue<Integer> buyList = new LinkedList<>();
	static long benefit;
	static long[] answer;

	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new long[T];
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			info = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				info[j] = Integer.parseInt(st.nextToken());
			}

			max = info[N];
			benefit = 0;
			// search from back
			for (int day = N - 1; day >= 1; day--) {

				if (max > info[day]) {
					buyList.add(info[day]);
				}
				
				if (max < info[day] || day == 1) {
					while (!buyList.isEmpty()) {
						int buyPrice = buyList.poll();
						benefit += max - buyPrice;
					}
					max = info[day];
				}

			}
			answer[i] = benefit;
		}

		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}

	}
}
