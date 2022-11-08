

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {

	static Queue<Integer> q = new LinkedList<>();
	static int[] list;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		list = new int[N+1];

		for (int test_case = 1; test_case <= N; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < 10; i++) {
				int num = Integer.parseInt(st.nextToken());
				if (num % 2 != 0) {
					list[test_case] += num;
				}

			}
		}

		for (int i = 1; i <= N; i++) {
			System.out.println("#" + i + " " + list[i]);
		}

	}
}
