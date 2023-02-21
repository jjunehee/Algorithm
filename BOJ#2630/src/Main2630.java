import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2630 {
	static int[][] map;
	static int white;
	static int blue;

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

		divide(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
		
	}

	private static void divide(int x, int y, int size) {

		int sum = 0;
		for (int i = x, xEnd = x + size; i < xEnd; i++) {
			for (int j = y, yEnd = y + size; j < yEnd; j++) {
				sum += map[i][j];
			}
		}

		if (sum == 0) {
			white++;
		} else if (sum == size * size) {
			blue++;
		} else {
			int half = size / 2;
			divide(x, y, half);
			divide(x + half, y, half);
			divide(x, y + half, half);
			divide(x + half, y + half, half);
		}

	}
}
