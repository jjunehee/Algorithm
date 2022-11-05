import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class AutoEver1 { //���Թ��� ���� testCase - Input adbfdf a####f, Output : YES
	static String board;
	static String pattern;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = br.readLine();
		pattern = br.readLine();
		boolean flag = false;
		for (int i = 0; i <= board.length() - pattern.length(); i++) { // �Ϳ�.... ������ ���̱����� �ε��������� pattern Ž���� �����ؾ����� board�� ������ �ε������� �� �����Ƿ� 
		    if (search(i)) flag = true; //������ �����ϴµ�, ���� �ϳ��� �����̶� �����̹Ƿ�, �׶� flag���� ��ŷ.
		}
		
		if(flag) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
	
	public static boolean isVowel(char x){ // ������ ��� true.   �Ϳ�.... !isVowel�� ������ ��츦 ǥ���ϴ� �͵� �����ִ� ����̱���.
	    return x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u';
	}
	
	public static boolean search(int index) { // index - Ž�� ���� �ε���
		for (int i = 0, j = index; i < pattern.length(); i++, j++) {  // pattern�� ���̸�ŭ �ϳ��� ���� ������
			if (pattern.charAt(i) == '@'){ // @�̸�~
			    if (!isVowel(board.charAt(j))) return false; // ������ ���;��ϴµ�, �ƴϸ� false -> ���� �ƴϴ�~ 
			}else if (pattern.charAt(i) == '#'){ //#�̸�~
			    if (isVowel(board.charAt(j))) return false; // ������ ���;��ϴµ�, �����̸� false -> ���� �ƴϴ�~
			}else if (pattern.charAt(i) != board.charAt(j)){ // ��? �ƾ� �׳� ���� ��ü�� �ٸ��� ������ false -> ���� �ƴϴ�.
			    return false;
			}
		}
		return true; // ���� ���� �� ����ߴ�? -> board�� pattern �����ϴ� ���ڿ� ����.
	}
}