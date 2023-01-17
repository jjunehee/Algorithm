package com.ssafy.hw.step2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// island bridge
public class Main2029 {
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = (int)(str.charAt(j) -'0'); // char to int
			}
		}
		
		int maxDistance = 0;
		
		//모든 섬에대해서 완전탐색
		for (int i = 0; i < N; i++) { 

			for (int j = 0; j < N; j++) {

				if (map[i][j] == 1) { // 섬이라면

					for (int dir = 0; dir < 4; dir++) { //동서남북 확인
						int k = 1; 
						while (true) {
							int nx = i + k * dx[dir]; // 동서남북으로 거리 k만큼 떨어진
							int ny = j + k * dy[dir];
							if (nx < 0 || nx >= N || ny < 0 || ny >= N) { // 맵에서 벗어나면 그 방향에 섬이 없다는 의미이므로 break
								break;
							}
							
							if (map[nx][ny] == 1) { // 그 방향에서 섬이 발견된다면 
								maxDistance = Math.max(maxDistance, k); // 모든 섬에대해서 진행하면서 최대 k값 갱신
								break;
							}
							k++;
						}
					}
				}
			}
		}
		System.out.println(maxDistance);

	}
}
