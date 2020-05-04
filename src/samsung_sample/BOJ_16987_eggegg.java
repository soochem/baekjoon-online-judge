package samsung_sample;

import java.util.*;
import java.io.*;

public class BOJ_16987_eggegg {
	public static int n;
	public static int[] pass;
	public static int [][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n+1][2];
		pass = new int[n+1];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken()); //내구도
			map[i][1] = Integer.parseInt(st.nextToken()); //무게
		}
		
		//int idx = 0;
		print(map);
		find(map);
		
	}
	private static void find (int[][] map) {
		int idx = 0; // 왼손
		int next = 0;
		//int cnt = 0;
		boolean flag = true;
		
		while (flag) {
			System.out.println(idx+" "+next);
			flag = false;
			if (idx >= n-1) {
				idx = 0;
			}
			if (map[idx][0] < 0) {
				idx++;
				continue;
			}
			
			for (int i=0; i<n ;i++) { // i는 0부터?
				if (i == idx) continue;
				if (map[i][0] > 0){
					System.out.println("? "+ i);
					next = i;
					flag = true;
					break;
				}
			}
			
			if (!flag) {
				continue;
			}
			
			if (map[idx][0] > 0) {
				map[idx][0] -= map[next][1];
				map[next][0] -= map[idx][1];
			}
			
			if (next >= n-1) break;
			idx = next+1;
			
			System.out.println(idx+" "+next);
			print(map);
		}
	}
	private static void print(int[][] map) {
		System.out.print("map : ");
		for (int i=0; i<n; i++) {
			System.out.print(map[i][0]+" ");
		}
		System.out.println();
	}
}
