import java.util.Scanner;

public class LISTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] LIS = new int[N]; // i를 끝으로 하는 LIS 길이

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			LIS[i] = 1; // 자기 자신만 끝에 있는 경우 길이 1
			for (int j = 0; j < i; j++) {
				if(arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			if(max < LIS[i]) max = LIS[i];
		}
		System.out.println(max);
	}
	
	
}
