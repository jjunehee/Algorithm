import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17135 {
	static int[][] map;
	static int[] pick;
	static int N, M;
	static boolean[] select;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		select = new boolean[M];
		pick = new int[3];
		comb(0, 0);
		
		attack();

	}


	private static void comb(int cnt, int idx) {
		if (cnt == 3) {
			System.out.println(Arrays.toString(pick));
			return;
		}

		for (int i = idx; i < M; i++) {
			pick[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
	
	private static void attack() {
		
		
	}
//	private static void comb(int cnt, int idx) {
//		if (cnt == 3) {
//			System.out.println(Arrays.toString(select));
//			return;
//		}
//		if(idx==M) {
//			return;
//		}
//		
//		select[idx] = true;
//		comb(cnt + 1, idx + 1);
//		select[idx] = false;
//		comb(cnt, idx + 1);
//
//	}	
	
	
}
