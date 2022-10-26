package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution32 {
	static int[] answer;
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	static int sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new int[10+1];

		for (int t = 1; t <= 10; t++) {
			map = new int[100][100];
			int order = Integer.parseInt(br.readLine());
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			max = 0;

			// row
			
			for (int i = 0; i < 100; i++) {
				sum = 0;
				for (int j = 0; j < 100; j++) {
					sum += map[i][j];
				}
				max = Math.max(max,sum);
			}
			
			for (int j = 0; j < 100; j++) {
				sum = 0;
				for (int i = 0; i < 100; i++) {
					sum += map[i][j];
				}
				max = Math.max(max,sum);
			}
			
			sum = 0;
			for(int i=0; i<100; i++) {
				sum += map[i][i];
			}
			max = Math.max(max,sum);

			sum = 0;
			for(int i=0; i<100; i++) {
				sum += map[i][99-i];
			}
			max = Math.max(max,sum);

			answer[t] = max;
		}

		for (int i = 1; i <= 10; i++) {
			System.out.println("#" + i + " " + answer[i]);
		}
	}
}
