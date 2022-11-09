import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Pelindrome {

	public static void main(String[] args) throws IOException {
		// �縰��� ���� �ľ��ϱ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length() / 2; i++) {
			char cur = str.charAt(i);
			stack.push(cur);
		}
		for (int i = str.length() / 2; i < str.length(); i++) {
			char cur = str.charAt(i);
			if(cur == stack.peek()) {
				stack.pop();
			}
		}
		if (stack.isEmpty()) {
			System.out.println("�縰��� �Դϴ�.");
		} else {
			System.out.println("�ƴմϴ�.");
		}
	}
}
