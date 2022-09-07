package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14890 {
	public static int N;
	public static int L;
	public static int[][] map;
	public static int count;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(map[0][0]);

	}

	private static void dfs(int cur) {
		int nextC;
		boolean canPass = true;
		for (int i = 0; i < N; i++) {
			int sameheight = 1;
			for (int j = 0; j < N; j++) {

				// За Check
				nextC = map[i][j + 1];
				if (nextC != cur) {
					if (sameheight >= L) {
						sameheight = 0;
						cur = nextC;
						continue;
					} else {
						canPass = false;
						break;
					}

				} else {
					sameheight++;
				}
				

			}
			if (canPass == true) {
				count++;
			}

		}
	}
}
