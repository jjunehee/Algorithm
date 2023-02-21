import java.util.Scanner;

public class Main2023 {
	static int N;
	static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		permu(0, "");
		System.out.println(sb.toString());
	}

	private static void permu(int cnt, String num) {
		if (cnt == N) {
			sb.append(num).append("\n");
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (isPrime(num + i)) {
				permu(cnt + 1, num + i);
			}
		}

	}

	private static boolean isPrime(String num) {

		int n = Integer.parseInt(num);

		if(n == 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
