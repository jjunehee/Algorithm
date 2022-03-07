package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	private static int n;
	private static int[][] map;
	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };
	private static int Max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		StringTokenizer st;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(dfs());
		br.close();

	}

	public static int dfs() {

		// 5번 움직이기, 상하좌우로 움직이는 경우로 나누어서, Max값을 계속해서 갱신하는 과정 속에 마지막 Max값이 return 값. 
		for (int i = 0; i < 5; i++) {
			
		}

		return Max;
	}
}