import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution36 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			String str = br.readLine();
			String[] st = str.split("");

			for (int index = 0; index < st.length; index++) {
				if (st[index] == "1") {
					dfs(index, st[index], st.length);
				}
			}
		}
	}

	public static void dfs(int i, String st, int end) {
		if(i == end) {
			return;
		}
		
		if() {
			
		}
	}
}
