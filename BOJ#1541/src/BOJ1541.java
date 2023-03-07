import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1541 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] subSplit = br.readLine().split("\\-");

		int min = Integer.MAX_VALUE;

		for (int i = 0; i < subSplit.length; i++) {

			String[] plusSplit = subSplit[i].split("\\+");

			int num = 0;
			for (int j = 0; j < plusSplit.length; j++) {
				num += Integer.parseInt(plusSplit[j]);
			}

			if (min == Integer.MAX_VALUE) {
				min = num;
			}else {
				min -= num;
			}
		}
		System.out.println(min);
	}
}