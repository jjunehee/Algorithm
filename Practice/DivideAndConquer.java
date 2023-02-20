import java.util.Scanner;

// 거듭제곱 구하기
public class DivideAndConquer {

	static int cnt1;
	static int cnt2;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int X = sc.nextInt();
		int N = sc.nextInt();
		
		System.out.println(Solution1(X,N) + " tt" + cnt1);
		System.out.println(Solution2(X,N) + " tt" + cnt2);
	}
	
	// 재귀를 통한 방법1
	private static int Solution1(int x, int n) {
		cnt1++;
		if(n==1) return x;
		
		return x* Solution1(x, n-1);
	}
	
	// 분할 정복을 통한 방법2
	private static int Solution2(int x, int n) {
		cnt2++;
		if(n==1) return x;
		
		if(n%2==0) {
			int y = Solution2(x,n/2);
			return y*y;
		} else {
			int y = Solution2(x,(n-1)/2);
			return y*y*x;
		}
		
	}
	
	
	
	
}
