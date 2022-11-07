import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution42 {
	static int[] answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			int[] Pcoordi = getcoordi(p);
			int[] Qcoordi = getcoordi(q);
			
			answer[t] = getValue(Pcoordi[0] + Qcoordi[0], Pcoordi[1] + Qcoordi[1]);
		}
		
		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}

	}

	public static int[] getcoordi(int value) {
		int count = 1;
		for (int i = 1;; i++) {
			for (int x = 1, y = i; x <= i; x++, y--) {
				if(value == count) {
					return new int[] {x,y};
				}
				count++;
			}
		}
	}
	
	public static int getValue(int X, int Y) {
		int count = 1;
		for (int i = 1;; i++) {
			for (int x = 1, y = i; x <= i; x++, y--) {
				if(x == X && y == Y) {
					return count;
				}
				count++;
			}
		}
	}
}
