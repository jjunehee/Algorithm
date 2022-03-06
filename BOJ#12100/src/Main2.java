package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

	static int[][] map;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		map = new int[num][num];
		
		StringTokenizer st;
		
		for(int i=0; i<num; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<num; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<num; i++) {
			for(int j=0; j<num; j++) {
				System.out.print(map[i][j] + " "); 
			}
			System.out.println();
		}
		
	
	}
}
