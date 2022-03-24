package src;

import java.io.*;
import java.util.*;

public class Main4 {
	public static int N, B, C;
	public static int[] studentNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		studentNum = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			studentNum[i] = Integer.parseInt(st.nextToken());
		}

		StringTokenizer st1 = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st1.nextToken());
		C = Integer.parseInt(st1.nextToken());

		System.out.println(solve());

	}

	private static long solve() {
		long count = 0;
		long cNum;
		for (int i = 0; i < N; i++) {
			studentNum[i] = studentNum[i] - B;
			count++;
			
			if (studentNum[i] <= 0)
				continue;

			cNum = studentNum[i] / C;
			count = count + cNum;
			if (studentNum[i] % C != 0) {
				count++;
				break;
			}

		}

		return count;
	}
}
