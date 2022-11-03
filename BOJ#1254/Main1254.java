
import java.util.*;
import java.io.*;

public class Main1254 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			if (isPelindrome(str.substring(i))) {
				System.out.println(str.length() + i);
				break;
			}
		}

	}

	public static boolean isPelindrome(String s) {

		int start = 0;
		int end = s.length() - 1;
		
		while(start <= end) {
			if(s.charAt(start) != s.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
		
	}
}