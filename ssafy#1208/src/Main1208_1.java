package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1208_1 {
	static int[] box;
	static int d;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= 10; t++) {
			d = Integer.parseInt(br.readLine());
			sb.append("#" + t + " ");
			box = new int[100];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				box[i] = Integer.parseInt(st.nextToken());
			}
		
			recur(0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void recur(int cnt) {

		if (cnt == d) {
			answer = box[99] - box[0];
			return;
		}

		box[99] -= 1;
		box[0] += 1;
		Arrays.sort(box);
		recur(cnt + 1);
	}
}
