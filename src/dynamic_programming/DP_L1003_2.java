// 2019. 01. 21
// DP ±‚√ 

package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_L1003_2 {
	public static int num0, num1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//int[] dp = new int [n+1];
		for (int i=0; i<n; i++) {
			num0 = 0;
			num1 = 0;
			int z = Integer.parseInt(br.readLine());
			fib(z);
			System.out.println(num0 + " "+ num1);
		}
	}
	private static int fib (int n) {
		if (n == 0) {
			num0++;
			return 0;
		}
		else if (n == 1) {
			num1++;
			return 1;
		}
		else {
			return fib(n-1) + fib(n-2);
		}
	}
}
