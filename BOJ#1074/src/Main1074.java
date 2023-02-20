import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Z
public class Main1074 {
	
	static int[][] map;
	static int N,r,c;
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[2^N][2^N];
		
		divide();
	}
	
	// 8*8를 4*4로 4등분 그리고 그것을 2*2로 4등분
	private static void divide() {
		
		
		// 어렵다..
		
	}
}





