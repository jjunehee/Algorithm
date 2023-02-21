import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3109 {
	static char[][] map;
	static int R, C;
	static int ret;
	static final char bridge = '=';
	static final char building = 'x';

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			if(simulation(i, 0, 0)) {
				ret++;
			}
		}
		System.out.println(ret);
	}

	// 1행부터 샤라라ㅏ락 갈 수 있는 곳 가버려~~ (3방향)
	private static boolean simulation(int x, int y, int cnt) {

		map[x][y] = bridge; // 이 재귀에 들어왔다는 것은 파이프가 설치되었다는 의미

		// 끝나는 시점은 어떤 조건일까.
		// 열만큼 이동한 시점일까?
		// 맞는거같다. 왜냐면 항상 대각이든 직진이든 열을 이동하니까.
		if (cnt == C - 1) {
			return true;
		}

		// 각 방향으로 갈껀데, 아무곳이나 못간다. 위치, 다리 설치 여부에 따라 달라짐.
		// 여기서 중요한건 완탐으로 싹 돌고 싶은데, 싹 다 돌면 너어어어무 많다.
		// 생각해보면, 위쪽에 있는 행은 위쪽에 길이 있는데 굳이 아래쪽으로 갈 필요가 없다.
		// 그건 아래에 있는 행이 가면 되는거니까.
		// 그러니까 위쪽을 우선적으로 움직이고 도달하면 나머지는 갈 필요가 없는것이다.
		// 그러므로 이 문제는 그리디!!!!!!!!
		if (x > 0 && map[x - 1][y + 1] != bridge && map[x - 1][y + 1] != building) {
			if(simulation(x - 1, y + 1, cnt + 1)) {
				return true;
			}
		}

		if (map[x][y + 1] != bridge && map[x][y + 1] != building) {
			if(simulation(x, y + 1, cnt + 1)) {
				return true;
			}
		}

		if (x < R - 1 && map[x + 1][y + 1] != bridge && map[x + 1][y + 1] != building) {
			if(simulation(x + 1, y + 1, cnt + 1)) {
				return true;
			}
		}
		
		return false;

	}
}
