import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2029 {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// row check
		boolean flag = false;
		int length = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (flag) {
					if (map[i][j] == 1) {
						length++;
					} else if (map[i][j] == 0) {
						
					}
				} else {
					if (map[i][j] == 1) {
						flag = true;
						continue;
					}
				}
			}
		}

	}
}
