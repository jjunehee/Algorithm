import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ18808 {

	static int x, y;
	static List<Sticker> stickerList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		int stickerAmount = Integer.parseInt(st.nextToken());

		stickerList = new ArrayList<>();
		for (int c = 0; c < stickerAmount; c++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int[][] map = new int[x][y];
			for (int i = 0; i < x; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < y; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Sticker sticker = new Sticker(x, y);
			sticker.map = map;

			stickerList.add(sticker);
		}

		simulation();

		// map에서 1인 갯수 count;
	}

	public static void simulation() {

		for (Sticker sticker : stickerList) {

			for (int turn = 0; turn < 3; turn++) {

				// 왼쪽 위부터 이동하며 스티커 x,y 크기만큼 map에서 띄어내어 붙일 수 있는지 판단
				if (checkPossibleAndAttach()) {
					break;
				}

				// 가장 오른쪽 아래까지 이동했는데 가능한 위치가 없다면, rotate 진행
				rotate();
			}

		}
	}

	public static boolean checkPossibleAndAttach() {
		return false;
	}

	public static void rotate() {

	}

	public static class Sticker {
		int x, y;
		int[][] map;

		public Sticker(int x, int y) {
			this.x = x;
			this.y = y;
			this.map = new int[x][y];
		}
	}
}
