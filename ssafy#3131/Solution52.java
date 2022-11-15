
public class Solution52 {
	public static void main(String[] args) {
		// 1이상 100만 이하의 소수를 구하는 프로그램 작성
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
