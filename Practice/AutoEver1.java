import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class AutoEver1 { //스팸문자 필터 testCase - Input adbfdf a####f, Output : YES
	static String board;
	static String pattern;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = br.readLine();
		pattern = br.readLine();
		boolean flag = false;
		for (int i = 0; i <= board.length() - pattern.length(); i++) { // 와우.... 길이의 차이까지의 인덱스까지만 pattern 탐색을 진행해야지만 board의 마지막 인덱스까지 딱 맞으므로 
		    if (search(i)) flag = true; //여러번 진행하는데, 그중 하나만 스팸이라도 스팸이므로, 그때 flag값을 마킹.
		}
		
		if(flag) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
	
	public static boolean isVowel(char x){ // 모음인 경우 true.   와우.... !isVowel로 자음인 경우를 표현하는 것도 센스있는 방법이군요.
	    return x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u';
	}
	
	public static boolean search(int index) { // index - 탐색 시작 인덱스
		for (int i = 0, j = index; i < pattern.length(); i++, j++) {  // pattern의 길이만큼 하나씩 문자 비교진행
			if (pattern.charAt(i) == '@'){ // @이면~
			    if (!isVowel(board.charAt(j))) return false; // 모음이 나와야하는데, 아니면 false -> 스팸 아니다~ 
			}else if (pattern.charAt(i) == '#'){ //#이면~
			    if (isVowel(board.charAt(j))) return false; // 자음이 나와야하는데, 모음이면 false -> 스팸 아니다~
			}else if (pattern.charAt(i) != board.charAt(j)){ // 음? 아아 그냥 문자 자체가 다르면 무조건 false -> 스팸 아니다.
			    return false;
			}
		}
		return true; // 위의 조건 다 통과했다? -> board에 pattern 만족하는 문자열 존재.
	}
}