package src;

import java.io.*;
import java.util.*;

//�Ʊ� ��� ����
public class Main6 {

	public static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		// map �Է�
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
