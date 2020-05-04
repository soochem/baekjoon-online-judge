// 2019. 01. 21
// 백준 동적프로그래밍
// 1463 1로 만들기

package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_L1463_2 {
	
	public static int min = 1000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		makeOne(n, 0);
	}
	
	private static int makeOne (int x, int c) {
		//int[] memo = new int [1000000];
		//int[] memo1 = new int [x+1];
		//int[] memo2 = new int [x+1];
		//int[] memo3 = new int [x+1];
		
		for (int i=0; i<=x/3; i++) {
			for (int j=0; j<=(x-i*3)/2; j++) {
				System.out.println(i+" "+j+" "+(x-i*3-j*2));
				c = i+j+(x-i*3-j*2);
				if (min > c) min = c;
			}
		}
		System.out.println(min);
		return min;
	}
}
