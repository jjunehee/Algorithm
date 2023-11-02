import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1924 {

	static int[] endDay = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	static String[] dayArray = { "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN" };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		String[][] calendar = new String[13][32];

		int cnt = 0;
		for (int month = 1; month <= 12; month++) {
			for (int day = 1; day <= 31; day++) {
				if(endDay[month] < day) {
					break;
				}
				calendar[month][day] = dayArray[cnt];
				cnt = (cnt + 1) % 7;
			}
		}
		
		System.out.println(calendar[x][y]);

	}
}
