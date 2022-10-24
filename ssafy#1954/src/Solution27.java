package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution27 {
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			sb.append("#" + (i + 1) + "\n");
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			int count = 1;
			int curX = 0;
			int curY = 0;
			int dir = 0;
			while (count <= N * N) {
				map[curX][curY] = count;
				count++;

				int nextX = curX + dx[dir];
				int nextY = curY + dy[dir];

				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || map[nextX][nextY] != 0) {
					dir = (dir + 1) % 4;
					nextX = curX + dx[dir];
					nextY = curY + dy[dir];
				}

				curX = nextX;
				curY = nextY;

			}

			for (int I = 0; I < N; I++) {
				for (int J = 0; J < N; J++) {
					sb.append(map[I][J] + " ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}