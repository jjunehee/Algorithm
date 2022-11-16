
public class Break {
	public static void main(String[] args) {
		int a = 0;
		int b = 1;
		int c = 3;
		for (int i = 0; i < 2; i++) { //break문은 분기문에 아무리 깊게 들어가있어도 break문 하나면 가장 밖 for문까지 탈출!
			if (a == 0) {
				if (b == 2) {
					System.out.println("h");
				} else {
					System.out.println("t");
					break;
				}
			}
		}

	}
}
