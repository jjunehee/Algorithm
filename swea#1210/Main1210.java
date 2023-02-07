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
			
			for (int j = 0; j < 100; j++) { // 2�� �ִ� ��ǥ �������� �Ųٷ� �ö���. ������ ���������� �ϸ� 2�� ���� ��찡 �ƴ϶� �ٸ� ��쵵 �����ϱ� ������ ������ ��������� �ϴµ�, �̶� copymap�� ������ҰŰ��Ƽ� ���ŷο�Ű��Ƽ�!
				if (map[99][j] == 2) { 
					Solution(99, j);
					sb.append(answer).append("\n"); // ��͸� ������, ��ǥ���� ������ ����������, �� index ���.
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

		map[x][y] = 3; // ������ ���� �ٽ� �ǵ��ư��� �ʵ��� ǥ���ϱ�
		
		
		// ������ ���� or ���� ���ʿ� ���� ���� �켱������ �޶����Ƿ�, upFlag�� �б⸦ �ļ� ��쿡 ���� ������ ���� ���� 
		if (upFlag) { // ������ ���� ���� ����
			if (y - 1 >= 0 && map[x][y - 1] == 1) { // ����
				upFlag = false;
				Solution(x, y - 1); // recur
			} else if (y + 1 < 100 && map[x][y + 1] == 1) { // ������
				upFlag = false;
				Solution(x, y + 1); // recur
			} else { // ����
				upFlag = true;
				Solution(x - 1, y); // recur
			}
			
		} else { // ������ ������ ���� ����
			if (x + 1 < 100 && map[x + 1][y] == 1) { // ����
				upFlag = true;
				Solution(x - 1, y); // recur
			} else if (y + 1 < 100 && map[x][y + 1] == 1) { // ������
				upFlag = false;
				Solution(x, y + 1); // recur
			} else if (y - 1 >= 0 && map[x][y - 1] == 1) { // ����
				upFlag = false;
				Solution(x, y - 1); // recur
			}
		}
	}
}
