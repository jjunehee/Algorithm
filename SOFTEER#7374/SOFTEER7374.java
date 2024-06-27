
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SOFTEER7374 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] map = new int[3][3];

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean Xflag = true;
		boolean gameOver = false;
		// 행 검사
		for (int i = 0; i < 3; i++) {
			int tmp = map[i][0];
			for (int j = 1; j < 3; j++) {
				if (tmp != map[i][j]) {
					Xflag = false;
				}
			}
			if (Xflag) {
				gameOver = true;
				System.out.println(0);
			}
		}

		// 열 검
		if (!gameOver) {
			boolean Yflag = true;
			for (int j = 0; j < 3; j++) {
				int tmp = map[0][j];
				for (int i = 1; i < 3; i++) {
					if (tmp != map[i][j]) {
						Yflag = false;
					}
				}
				if (Yflag) {
					gameOver = true;
					System.out.println(0);
				}
			}
		}

		if (!gameOver) {

			int min = Integer.MAX_VALUE;
			
			// 행 계산
			for (int i = 0; i < 3; i++) {
				int tmp = map[i][0];
				for (int j = 1; j < 3; j++) {
					if (tmp != map[i][j]) {
						tmp = Math.abs(tmp - map[i][j]);
					}
				}
				min = Math.min(tmp, min);

			}

			// 열 계산
			for (int j = 0; j < 3; j++) {
				int tmp = map[0][j];
				for (int i = 1; i < 3; i++) {
					if (tmp != map[i][j]) {
						tmp = Math.abs(tmp - map[i][j]);
					}
				}
				min = Math.min(tmp, min);
			}
			
			System.out.println(min);
		}
	}
}
