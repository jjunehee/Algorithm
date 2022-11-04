import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution35 {
	static int[][] map;
	static int[] answer;

	public static void main(StringProblem[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new int[10];
		for (int t = 0; t < 10; t++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int j = 0; j < 100; j++) {
				boolean flag = false;
				for (int i = 0; i < 100; i++) {
					if (map[i][j] == 1) {
						flag = true;
					}

					if (flag == true && map[i][j] == 2) {
						answer[t]++;
						flag = false;
					}
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			System.out.println("#" + (i+1) + " " + answer[i]);
		}
	}
}
