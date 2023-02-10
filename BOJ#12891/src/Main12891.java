import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main12891 {
	static String[] subStr;
	static int[] ACTG;
	static int S, P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		String dnaStr = br.readLine();

		ACTG = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			ACTG[i] = Integer.parseInt(st.nextToken());
		}

		subStr = new String[S - P + 1];
		for (int i = 0; i <= S - P; i++) {
			subStr[i] = dnaStr.substring(i, i + P);
		}
		
		
		// 이 방법으로 했는데, 메모리 초과.... 다른 방법으로 시도중..
//		int cnt = 0;
//		for (int t = 0; t < subStr.length; t++) {
//			boolean flag = true;
//			String str = subStr[t];
//			int[] copyACTG = ACTG.clone();
//			for (int i = 0; i < P; i++) {
//				switch (str.charAt(i)) {
//				case 'A':
//					copyACTG[0]--;
//					break;
//				case 'C':
//					copyACTG[1]--;
//					break;
//				case 'G':
//					copyACTG[2]--;
//					break;
//				case 'T':
//					copyACTG[3]--;
//					break;
//				}
//			}
//			for (int i = 0; i < 4; i++) {
//				if(copyACTG[i]==0) {
//					continue;
//				}
//				flag = false;
//			}
//			if(flag) {
//				cnt++;
//			}
//		}
//		System.out.println(cnt);
		
		
	}

	
}
