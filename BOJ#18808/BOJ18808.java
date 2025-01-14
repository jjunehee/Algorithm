import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ18808 {

	static int x, y;
	static int[][] map;
	static List<Sticker> stickerList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		map = new int[x][y];
		int stickerAmount = Integer.parseInt(st.nextToken());

		stickerList = new ArrayList<>();
		for (int c = 0; c < stickerAmount; c++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int[][] stickerMap = new int[x][y];
			for (int i = 0; i < x; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < y; j++) {
					stickerMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Sticker sticker = new Sticker(x, y);
			sticker.map = stickerMap;

			stickerList.add(sticker);
		}

		simulation();

		int ret = countAttach();
		System.out.print(ret);
		
	}
	

	public static void simulation() {

		for (Sticker sticker : stickerList) {

			for (int dir = 0; dir < 4; dir++) {

				boolean isAttach = false;
				outerLoop: for (int i = 0; i <= x - sticker.x; i++) {
					for (int j = 0; j <= y - sticker.y; j++) {

						// 왼쪽 위부터 이동하며 스티커 x,y 크기만큼 map에서 띄어내어 붙일 수 있는지 판단
						if (checkPossible(i, j, sticker)) {
							attach(i, j, sticker);
							isAttach = true;
							break outerLoop;
						}
					}
				}

				if (isAttach) { // 가장 오른쪽 아래까지 이동했는데 가능한 위치가 없다면, rotate 진행
					break;
				} else {
					rotate(sticker);
				}

			}

		}
	}


	public static boolean checkPossible(int startX, int startY, Sticker sticker) {

		boolean check = true;

		outerLoop: for (int i = startX, a = 0; i < startX + sticker.x && a < sticker.x; i++, a++) {
			for (int j = startY, b = 0; j < startY + sticker.y && b < sticker.y; j++, b++) {
				if (sticker.map[a][b] == 1 && map[i][j] == 1) {
					check = false;
					break outerLoop;
				}
			}
		}

		// test branch - a
		return check;
	}

	public static void attach(int startX, int startY, Sticker sticker) {

		for (int i = startX, a = 0; i < startX + sticker.x && a < sticker.x; i++, a++) {
			for (int j = startY, b = 0; j < startY + sticker.y && b < sticker.y; j++, b++) {
				if(sticker.map[a][b] == 1) {
					map[i][j] = sticker.map[a][b];
				}
			}
		}
	}

	public static void rotate(Sticker sticker) {
		int newStickerX = sticker.y;
		int newStickerY = sticker.x;
		int[][] newStickerMap = new int[newStickerX][newStickerY];

		for (int i = 0, b = 0; i < newStickerX && b < sticker.y; i++, b++) {
			for (int j = 0, a = sticker.x - 1; j < newStickerY && a >= 0; j++, a--) {
				newStickerMap[i][j] = sticker.map[a][b];
			}
		}

		sticker.x = newStickerX;
		sticker.y = newStickerY;
		sticker.map = new int[newStickerX][newStickerY];
		sticker.map = newStickerMap;
	}
	
	public static int countAttach() {
		int cnt = 0;
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				if(map[i][j] == 1) {
					cnt++;
				}
			}
		}
		
		return cnt;
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
