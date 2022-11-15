import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution53 {
	static int min, max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			char[] Num = br.readLine().toCharArray();

			min = Integer.parseInt(String.valueOf(Num));
			max = Integer.parseInt(String.valueOf(Num));
			search(Num);
			
			sb.append(min + " " + max).append("\n");
		}
		System.out.println(sb);
	}

	public static void search(char[] Num) {

		for (int i = 0; i < Num.length - 1; i++) {

			for (int j = i + 1; j < Num.length; j++) {

				char tmp = Num[i];
				Num[i] = Num[j];
				Num[j] = tmp;

				String checkNum = String.valueOf(Num);

				if (checkNum.charAt(0) != '0') {
					min = Math.min(min, Integer.parseInt(checkNum));
					max = Math.max(max, Integer.parseInt(checkNum));
				}

				tmp = Num[i];
				Num[i] = Num[j];
				Num[j] = tmp;

			}
		}

	}
}
