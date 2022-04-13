package src;

import java.io.*;
import java.util.*;

//아기 상어 문제
public class Main6 {

	public static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		// map 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9)
					Shark shark = new Shark();
			}
		}

	}
	
	public static class Shark{
		
		public Shark() {
			
		}
	}
	
	

}
