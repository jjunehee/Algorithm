import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1_1 {

	static int[] levelInfo; // ������ ù��° ��
	
	public static void main(String[] args) throws IOException {

		makeLevelInfo(); // �� ������ ù��° ���������� ǥ���Ѵ�.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int s, e;
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());

			if (s == e) { // �����, ������ ������ �� �ʿ䰡 ����~
				sb.append(0).append("\n");
				continue;
			}

			int tmp;
			if (s > e) { // ������� �׻� �۵��� �����. �Ʒ��� Ž��!
				tmp = s;
				s = e;
				e = tmp;
			}

			int startLevel = getLevel(s); // ���������� ����
			int endLevel = getLevel(e); // ���������� ����
			int Left = s, Right = s;
			for (int level = startLevel; level < endLevel; level++) { // ���������� �������� �Ƕ�̵� ������� Ž��!
				Left = Left + level;
				Right = Right + (level + 1);
			}
			
			if (e < Left) {
				sb.append(endLevel - startLevel + (Left - e)).append("\n");
			} else if (e > Right) {
				sb.append(endLevel - startLevel + (e - Right)).append("\n");
			} else {
				sb.append(endLevel - startLevel).append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	public static int getLevel(int v) {
		for (int i = 1; i < 143; i++) {
			if (levelInfo[i] > v) {
				return i - 1;
			}
		}
		return -1;
	}

	public static void makeLevelInfo() {
		levelInfo = new int[143];
		levelInfo[0] = 1;
		for (int i = 1; i < 143; i++) {
			levelInfo[i] = levelInfo[i - 1] + i - 1;
		}
	}
}