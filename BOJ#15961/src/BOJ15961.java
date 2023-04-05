import java.util.Scanner;

public class BOJ15961 {

	static int[] sushi, count;
	static int N, d, k, c;
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		d = sc.nextInt();
		k = sc.nextInt();
		c = sc.nextInt();

		sushi = new int[N];
		for (int i = 0; i < N; i++) {
			sushi[i] = sc.nextInt();
		}

		count = new int[d + 1]; // 초밥번호가 d번까지 있음

		count[c]++; // 쿠폰을 나중에 구간 변경해가면서 체크하려면 복잡. 이건 일단 무조건 먹은걸로 해놓고 가짓수 세자.
		int tmp = 1; // 먹은 초밥 가짓수 (쿠폰에 있는 초밥은 무조건 먹음)
		for (int i = 0; i < k; i++) { // i는 접시번호
			if (count[sushi[i]] == 0) { // i 접시에 담긴 sushi[i] 초밥을 먹은 카운트가 0이었어?
				tmp++; // 일단 가짓 수 증가
			}
			count[sushi[i]]++; // 해당 초밥 카운트 기록
		}
		
		ans = tmp;

		for (int s = 1; s < N; s++) { // 이제 다음 구간의 시작 위치를 s부터로 하자.
			count[sushi[s - 1]]--; // 새로운 시작위치 바로 앞 접시의 초밥 뱉어
			if (count[sushi[s - 1]] == 0) { // 헉.. 아깝다.. 이 초밥 딱 한번 먹었구나..
				tmp--; // 가짓수 감소
			}
			int newDish = (s + k - 1) % N; // 이제 저 뒤에 새로 먹게되는 접시번호
			if (count[sushi[newDish]] == 0) {
				tmp++;
			}
			count[sushi[newDish]]++;
			ans = Math.max(ans, tmp);
		}

		System.out.println(ans);

	}
}
