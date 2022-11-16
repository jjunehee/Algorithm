import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution57 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			if(str.substring(0,str.length()/2).equals(str.substring(str.length()/2+1))) {
				System.out.println(str.substring(str.length()/2+1));
				System.out.println("1");
			} else {
				System.out.println(str.substring(str.length()/2+1));

				System.out.println("0");
			}
		}
	}
}
