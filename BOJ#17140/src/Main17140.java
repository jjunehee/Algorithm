package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main17140 {
	public static int[][] A;
	public static int r, c, k;
	public static int Rlength = 3;
	public static int Clength = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		A = new int[101][101];
		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(simulation());
	}

	private static int simulation() {
		int time = 0;
		while(time<=100) {
			if(A[r][c]==k) {
				return time;
			}
			operating();
			time++;
			
		}
		return -1;
	}

	private static void operating() {

		if (Rlength >= Clength) {
			for (int i = 1; i <= Rlength; i++) {
				R(i);
			}
		} else {
			for (int i = 1; i <= Clength; i++) {
				C(i);
			}
		}
	}

	private static void R(int r) {
		Map<Integer, Integer> map = new HashMap<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		for (int c = 1; c <= Clength; c++) {
			if (A[r][c] == 0)
				continue;
			map.compute(A[r][c], (num, count) -> count == null ? 1 : count + 1);
		}
		map.forEach((num, count) -> pq.add(new Pair(num, count)));

		int c = 1;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			A[r][c++] = p.num;
			A[r][c++] = p.count;
		}

		Clength = Math.max(Clength, c);

		while (c <= 99) {
			A[r][c++] = 0;
			A[r][c++] = 0;
		}

	}

	private static void C(int c) {

		Map<Integer, Integer> map = new HashMap<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		for (int r = 1; r <= Rlength; r++) {
			if (A[r][c] == 0)
				continue;
			map.compute(A[r][c], (num, count) -> count == null ? 1 : count + 1);
		}
		map.forEach((num, count) -> pq.add(new Pair(num, count)));

		int r = 1;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			A[r++][c] = p.num;
			A[r++][c] = p.count;
		}

		Rlength = Math.max(Rlength, r);

		while (r <= 99) {
			A[r++][c] = 0;
			A[r++][c] = 0;
		}
	}

	public static class Pair implements Comparable<Pair> {
		int num;
		int count;

		public Pair(int num, int count) {
			this.num = num;
			this.count = count;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.count > o.count) {
				return 1;
			} else if (this.count == o.count) {
				return this.num - o.num;
			}
			return -1;
		}

	}
}
