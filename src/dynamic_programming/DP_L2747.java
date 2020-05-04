// 2019. 01. 19
// 백준 피보나치수

package dynamic_programming;

import java.util.Scanner;

public class DP_L2747 {
	
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		System.out.println(fibo(n));
		
		s.close();
	}
	
	private static int fibo (int n) {
		
		int[] fib = new int [1000];
		fib[0] = 0;
		fib[1] = 1;
		
		if (n >= 2) {
			for (int i=2; i<=n; i++) {
				fib[i] = fib[i-1] + fib[i-2];
			}
		}
		return fib[n];
	}
}
