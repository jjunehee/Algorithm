package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution29 {
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	static int amount;
	static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			
			max = 0;
			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) {
					amount = 0;
					Catch(i, j, M);
					max = Math.max(max, amount);
				}
			}
			answer[t] = max;
		}

		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i+1) + " " + answer[i]);
		}
	}

	public static void Catch(int i, int j, int M) {
		// 잡은 파리 계산
		for (int I = i; I < i + M; I++) {
			for (int J = j; J < j + M; J++) {
				amount += map[I][J];
			}
		}

	}
}
