package src;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution22 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= 10; t++) {
			int d = Integer.parseInt(br.readLine());
			sb.append("#" + t + " ");
			int[] box = new int[100];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				box[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(box);
			for (int i = 0; i < d; i++) {
				box[99] -= 1;
				box[0] += 1;
				Arrays.sort(box);
			}

			sb.append(box[99] - box[0]).append("\n");
		}
		System.out.println(sb);
	}
}