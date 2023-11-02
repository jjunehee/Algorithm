import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class softeer2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] nums = new int[8];
		for (int i = 0; i < 8; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		outerloop:
		if (nums[0] == 1 && nums[7] == 8) {
			for(int i=0; i<8; i++) {
				if(nums[i] != i+1) {
					System.out.println("mixed");
					break outerloop;
				}
			}
			System.out.println("ascending");
		} else if (nums[0] == 8 && nums[7] == 1) {
			for(int i=0; i<8; i++) {
				if(nums[i] != 8-i) {
					System.out.println("mixed");
					break outerloop;
				}
			}
			System.out.println("descending");
		} else {
			System.out.println("mixed");
		}

	}
}
