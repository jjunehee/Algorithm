package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main2 {

	static int N;
	static int[][] map;
	static int max;
	static Deque<Integer> dq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		// map 만들기
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dq = new ArrayDeque<Integer>();
		dfs(0, map);
		System.out.println(max);

		br.close();

	}

	static void dfs(int cnt, int[][] arr) {

		if (cnt == 5) {
			int temp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					temp = Math.max(temp, arr[i][j]);
			}
			max= Math.max(max,temp);
			return;
		}

		for (int i = 0; i < 4; i++) {
			dfs(cnt + 1, move(i, arr));
		}
	

	}

	// 상하좌우 이동
	static int[][] move(int dr, int[][] arr) {
		if (dr == 0)
			return moveUp(arr);
		else if (dr == 1)
			return moveDown(arr);
		else if (dr == 2)
			return moveLeft(arr);
		else
			return moveRight(arr);
	}

	static int[][] moveUp(int[][] arr) {
		int[][] temp = new int[N][N];
		for (int j = 0; j < N; j++) {
			dq.clear();
			for (int i = 0; i < N; i++) {
				if (arr[i][j] == 0)
					continue;
				if (!dq.isEmpty() && dq.getLast().intValue() == arr[i][j]) { // -2를 곱하는 이유는 한 무브에 한번만 합칠 수 있기 때문.
					dq.pollLast();
					dq.offer(arr[i][j] * -2);
				} else
					dq.offer(arr[i][j]);
			}

			int idx = 0;

			while (!dq.isEmpty()) {
				temp[idx++][j] = Math.abs(dq.pollFirst());
			}
			for (int i = idx; i < N; i++) {
				temp[i][j] = 0;
			}
		}

		return temp;

	}

	static int[][] moveDown(int[][] arr) {
		int[][] temp = new int[N][N];
		for (int j = 0; j < N; j++) {
			dq.clear();
			for (int i = N - 1; i >= 0; i--) {
				if (arr[i][j] == 0)
					continue;
				if (!dq.isEmpty() && dq.getLast().intValue() == arr[i][j]) { // -2를 곱하는 이유는 한 무브에 한번만 합칠 수 있기 때문.
					dq.pollLast();
					dq.offer(arr[i][j] * -2);
				} else
					dq.offer(arr[i][j]);
			}

			int idx = N - 1;

			while (!dq.isEmpty()) {
				temp[idx--][j] = Math.abs(dq.pollFirst());
			}
			for (int i = idx; i >= 0; i--) {
				temp[i][j] = 0;
			}
		}

		return temp;

	}

	static int[][] moveLeft(int[][] arr) {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			dq.clear();
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0)
					continue;
				if (!dq.isEmpty() && dq.getLast().intValue() == arr[i][j]) { // -2를 곱하는 이유는 한 무브에 한번만 합칠 수 있기 때문.
					dq.pollLast();
					dq.offer(arr[i][j] * -2);
				} else
					dq.offer(arr[i][j]);
			}

			int idx = 0;

			while (!dq.isEmpty()) {
				temp[i][idx++] = Math.abs(dq.pollFirst());
			}
			for (int j = idx; j < N; j++) {
				temp[i][j] = 0;
			}
		}

		return temp;

	}

	static int[][] moveRight(int[][] arr) {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			dq.clear();
			for (int j = N - 1; j >= 0; j--) {
				if (arr[i][j] == 0)
					continue;
				if (!dq.isEmpty() && dq.getLast().intValue() == arr[i][j]) { // -2를 곱하는 이유는 한 무브에 한번만 합칠 수 있기 때문.
					dq.pollLast();
					dq.offer(arr[i][j] * -2);
				} else
					dq.offer(arr[i][j]);
			}

			int idx = N - 1;

			while (!dq.isEmpty()) {
				temp[i][idx--] = Math.abs(dq.pollFirst());
			}
			for (int j = idx; j >= 0; j--) {
				temp[i][j] = 0;
			}
		}

		return temp;
	}

}