import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1210 {
	static int[][] map;
	static int answer;
	static boolean upFlag = true;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		for (int T = 1; T <= 10; T++) {
			sb.append("#" + T + " ");
			int t = Integer.parseInt(br.readLine());
			map = new int[100][100];
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int j = 0; j < 100; j++) { // 2가 있는 목표 지점부터 거꾸로 올라가자. 이유는 위에서부터 하면 2로 가는 경우가 아니라 다른 경우도 존재하기 때문에 여러번 시행해줘야 하는데, 이때 copymap을 써줘야할거같아서 번거로울거같아서!
				if (map[99][j] == 2) { 
					Solution(99, j);
					sb.append(answer).append("\n"); // 재귀를 끝내고, 목표지인 맨위에 도착했을때, 열 index 출력.
				}
			}
		}
		System.out.println(sb.toString());
	}

	private static void Solution(int x, int y) { 
		if (x == 0) { // base condition
			answer = y;
			return;
		}

		map[x][y] = 3; // 지나온 길을 다시 되돌아가지 않도록 표시하기
		
		
		// 방향이 위쪽 or 오른 왼쪽에 따라 방향 우선순위가 달라지므로, upFlag로 분기를 쳐서 경우에 따른 움직임 기준 변경 
		if (upFlag) { // 직전에 위로 향한 상태
			if (y - 1 >= 0 && map[x][y - 1] == 1) { // 왼쪽
				upFlag = false;
				Solution(x, y - 1); // recur
			} else if (y + 1 < 100 && map[x][y + 1] == 1) { // 오른쪽
				upFlag = false;
				Solution(x, y + 1); // recur
			} else { // 위쪽
				upFlag = true;
				Solution(x - 1, y); // recur
			}
			
		} else { // 직전에 옆으로 향한 상태
			if (x + 1 < 100 && map[x + 1][y] == 1) { // 위쪽
				upFlag = true;
				Solution(x - 1, y); // recur
			} else if (y + 1 < 100 && map[x][y + 1] == 1) { // 오른쪽
				upFlag = false;
				Solution(x, y + 1); // recur
			} else if (y - 1 >= 0 && map[x][y - 1] == 1) { // 왼쪽
				upFlag = false;
				Solution(x, y - 1); // recur
			}
		}
	}
}
