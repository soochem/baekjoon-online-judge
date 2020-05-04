// 2019. 01. 21
// 백준 동적프로그래밍
// 1463 1로 만들기

package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_L1463 {
	
	public static int min = 1000001;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		makeOne(n, 0);
		System.out.println(min);
		
	}
	
	private static void makeOne (int x, int c) {
		if (x == 1) {
			if (min > c) min = c;
			return;
		}
		c++;
		if (x%3 == 0) makeOne(x/3, c);
		if (x%2 == 0) makeOne(x/2, c);
		makeOne(x-1, c);
	}
}
