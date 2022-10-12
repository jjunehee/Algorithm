package src;

import java.util.*;
import java.io.*;

public class Main17140 {
	static int r, c, k;
	static int[][] A;
	static int Rlength = 3;
	static int Clength = 3;

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

	public static int simulation() {
		int time = 0;
		while (time <= 100) {
			if (A[r][c] == k) {
				return time;
			}
			operating();
			time++;

		}
		return -1;
	}

	public static void operating() {
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

	public static void R(int r) {
		Map<Integer, Integer> map = new HashMap<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		for (int i = 1; i <= Clength; i++) {
			if (A[r][i] == 0)
				continue;
			map.compute(A[r][i], (num, count) -> count == null ? 1 : count + 1);
		}

		map.forEach((num, count) -> pq.add(new Pair(num, count)));

		int c = 1;
		while (!pq.isEmpty()) {
			Pair q = pq.poll();
			A[r][c++] = q.num;
			A[r][c++] = q.count;
		}

		Clength = Math.max(Clength, c);

		while (c <= 100) {
			A[r][c++] = 0;
		}

	}

	public static void C(int c) {
		Map<Integer, Integer> map = new HashMap<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		for (int i = 1; i <= Rlength; i++) {
			if (A[i][c] == 0)
				continue;
			map.compute(A[i][c], (num, count) -> count == null ? 1 : count + 1);
		}

		map.forEach((num, count) -> pq.add(new Pair(num, count)));

		int r = 1;
		while (!pq.isEmpty()) {
			Pair q = pq.poll();
			A[r++][c] = q.num;
			A[r++][c] = q.count;
		}

		Rlength = Math.max(Rlength, r);

		while (r <= 100) {
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
			} else
				return -1;
		}

	}
}
