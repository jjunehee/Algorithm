package com.ssafy.ws.step3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Building bridge
public class Main2008 {
	static char[][] map;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			sb.append("#" + t + " ");
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			}

			int maxHeight = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					boolean isAroundG = false;
					if (map[i][j] == 'B') {
						int height = 1;
						for (int dir = 0; dir < 8; dir++) {
							int nx = i + dx[dir];
							int ny = j + dy[dir];

							if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
								continue;
							}

							if (map[nx][ny] == 'G') {
								isAroundG = true;
								height = 2;
								break;
							}
						}
						if (!isAroundG) {

							for (int dir = 0; dir < 8; dir += 2) {
								int k = 1;
								while (true) {
									int nx = i + k * dx[dir];
									int ny = j + k * dy[dir];

									if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
										break;
									}
									if (map[nx][ny] == 'B') {
										height++;
									}
									k++;
								}
							}
						}
						maxHeight = Math.max(maxHeight, height);
					}

				}
			}
			sb.append(maxHeight).append("\n");
		}
		System.out.println(sb.toString());

	}
}
