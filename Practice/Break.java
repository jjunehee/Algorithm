
public class Break {
	public static void main(String[] args) {
		int a = 0;
		int b = 1;
		int c = 3;
		for (int i = 0; i < 2; i++) { //break���� �б⹮�� �ƹ��� ��� ���־ break�� �ϳ��� ���� �� for������ Ż��!
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
