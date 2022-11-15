
public class Solution52 {
	public static void main(String[] args) {
		// 1�̻� 100�� ������ �Ҽ��� ���ϴ� ���α׷� �ۼ�
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= 1000000; i++) {

			boolean isPrime = true;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if(i%j==0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				sb.append(i).append(" ");
			}
		}
		System.out.print(sb.toString());
	}
}
