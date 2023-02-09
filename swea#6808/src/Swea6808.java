import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea6808 {
	static boolean[] cards;
	static int[] A;
	static int[] B;
	static int Awin;
	static int Alose;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			cards = new boolean[19];

			A = new int[10];
			B = new int[10];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 9; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				cards[A[i]] = true;
			}
			Awin = 0;
			Alose = 0;
			permu(1);
			sb.append(Awin + " " + Alose).append("\n");
		}
		System.out.println(sb.toString());

	}

	private static void permu(int cnt) {
		if (cnt == 10) {
			gameStart();
			return;
		}

		for (int i = 1; i <= 18; i++) {
			if (cards[i])
				continue;

			B[cnt] = i;
			cards[i] = true;
			permu(cnt + 1);
			cards[i] = false;

		}

	}

	private static void gameStart() {
		int Ascore = 0;
		int Bscore = 0;

		for (int i = 1; i <= 9; i++) {
			if (A[i] > B[i]) {
				Ascore += (A[i] + B[i]);
			} else {
				Bscore += (A[i] + B[i]);
			}
		}

		if (Ascore > Bscore) {
			Awin++;
		} else if (Ascore < Bscore) {
			Alose++;
		}
	}
}
