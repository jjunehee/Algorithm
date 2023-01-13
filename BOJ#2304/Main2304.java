import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main2304 {

	static ArrayList<Pillar> pList = new ArrayList<>();

	// 창고 다각형 문제
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			pList.add(new Pillar(L, H));
		}

		Collections.sort(pList);

		for (Pillar p : pList) {
			System.out.println(p.L);
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
