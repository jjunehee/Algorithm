
public class Main2006 {
	public static void main(String[] args) {

//		int num = 1;
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				if(i>j) {
//					System.out.print("  ");
//				}else {
//					System.out.print(num++ + " ");
//				}
//			}
//			System.out.println();
//		}

		int num = 1;
		for (int i = 0, j = 0; i < 5; i++) {
			for (int k = 0; k < (5-j); k++) {
				System.out.print((k < j) ? "  " : num++ + " ");
			}
			if(i<2) {
				
				j++;
			}else {
				j--;
			}
			System.out.println();
		}
	}
}
