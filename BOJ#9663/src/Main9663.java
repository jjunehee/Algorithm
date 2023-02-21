import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9663 {
	static int[] Queen;
	static int N;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		Queen = new int[N + 1];

		setQueen(1);
		System.out.println(answer);
	}

	private static void setQueen(int rowNo) {

		if(!isAvailable(rowNo-1)) { // Backtracking!!!
			return;
		}
		
		if (rowNo > N) {
			answer += 1;
			return;
		}

		for (int i = 1; i <= N; i++) {
			Queen[rowNo] = i;
			setQueen(rowNo + 1);
		}
	}

	private static boolean isAvailable(int rowNo) {

		for (int r = 1; r < rowNo; r++) {
			if (Queen[r] == Queen[rowNo] || Math.abs(Queen[r] - Queen[rowNo]) == rowNo - r) {
				return false;
			}
		}

		return true;
	}
}
