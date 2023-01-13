import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main2304 {

	static ArrayList<Pillar> pList = new ArrayList<>();
	static int maxHeightIndex;
	static int result;
	static int minIndex, maxIndex;

	// 창고 다각형 문제
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int maxHeight = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			pList.add(new Pillar(L, H)); // 기둥정보를 저장

			if (maxHeight < H) {
				maxHeight = H;
				maxHeightIndex = L;
			}
		}

		Collections.sort(pList); // 입력에 위치 순서가 보장되지 않으니 정렬해준다.

		minIndex = pList.get(0).L;
		maxIndex = pList.get(pList.size() - 1).L;

		// 가장 높은 기둥 기준 왼쪽 오른쪽 탐색
		searchLeft();
		searchRight();
		result += maxHeight;
		
		System.out.println(maxHeightIndex);

	}

	private static void searchLeft() {
		int height = 0;
		for (int i = minIndex; i < maxHeightIndex; i++) {
			if (height < pList.get(i).H) {
				height = pList.get(i).H;
			}
			result += height;
		}
	}

	private static void searchRight() {
		int height = 0;
		for (int i = maxIndex; i > maxHeightIndex; i--) {
			if (height < pList.get(i).H) {
				height = pList.get(i).H;
			}
			result += height;
		}
	}

	public static class Pillar implements Comparable<Pillar> {

		int L, H;

		public Pillar(int L, int H) {
			this.L = L;
			this.H = H;
		}

		@Override
		public int compareTo(Pillar o) {
			return this.L - o.L;
		}
	}
}
