package src;

import java.io.*;
import java.util.*;

public class Main14889 {
	public static int N;
	public static int[][] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		S = new int[N+1][N+1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int[] i: S) {
			for(int j: i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
//		dfs();

	}

	private static void dfs() {

	}
}
