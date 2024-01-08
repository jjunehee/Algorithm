import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1_1 {

	static int[] levelInfo; // 레벨의 첫번째 값
	
	public static void main(String[] args) throws IOException {

		makeLevelInfo(); // 각 레벨의 첫번째 도착정점을 표시한다.

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

			if (s == e) { // 출발지, 도착지 같으면 할 필요가 없어~
				sb.append(0).append("\n");
				continue;
			}

			int tmp;
			if (s > e) { // 출발지가 항상 작도록 만든다. 아래로 탐색!
				tmp = s;
				s = e;
				e = tmp;
			}

			int startLevel = getLevel(s); // 시작정점의 레벨
			int endLevel = getLevel(e); // 도착정점의 레벨
			int Left = s, Right = s;
			for (int level = startLevel; level < endLevel; level++) { // 시작정점을 기준으로 피라미드 모양으로 탐색!
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