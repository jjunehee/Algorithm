
import java.util.*;

class PROG49994 {

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static Pos now;
	static int answer;
	static Map<String, Integer> map = new HashMap<>();

	public int solution(String dirs) {

		now = new Pos(5, 5);

		for (int i = 0; i < dirs.length(); i++) {
			char dir = dirs.charAt(i);
			switch (dir) {
			case 'L':
				move(1);
				break;
			case 'R':
				move(3);
				break;
			case 'D':
				move(0);
				break;
			case 'U':
				move(2);
				break;
			}
		}

		return answer;
	}

	public void move(int dir) {
		int nx = now.x + dx[dir];
		int ny = now.y + dy[dir];

		if (isBound(nx, ny)) {
			return;
		}

		String history1 = String.valueOf(now.x) + String.valueOf(now.y) + String.valueOf(nx) + String.valueOf(ny);
		String history2 = String.valueOf(nx) + String.valueOf(ny) + String.valueOf(now.x) + String.valueOf(now.y);
		if (!map.containsKey(history1) && !map.containsKey(history2)) {
			map.put(history1, 1);
			map.put(history2, 1);
			answer++;
		}
		now.x = nx;
		now.y = ny;
	}

	public boolean isBound(int nx, int ny) {
		if (nx < 0 || nx > 10 || ny < 0 || ny > 10) {
			return true;
		} else {
			return false;
		}
	}

	public class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
