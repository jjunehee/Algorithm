import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringProblem {

	public static void main(String[] args) throws IOException {
		// Input - Hyundai-Auto-Ever
		// Output - HAE
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String answer = "";

		for (int i = 0; i < str.length(); i++) {
			if(Character.isUpperCase(str.charAt(i))) {
				answer+= str.charAt(i);
			}
		}
		
		System.out.println(answer);
	}
}
