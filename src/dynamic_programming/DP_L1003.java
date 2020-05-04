// 2019. 01. 21
// DP 기초
// 0, 1의 개수에도 DP 사용

package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_L1003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i=0; i<n; i++) {
			int x = Integer.parseInt(br.readLine());
			fib(1, 0, x);
			fib(0, 1, x);
			System.out.println();
		}
		
	}
	private static void fib (int a1, int a2, int n) {
		int[] memo = new int [n+1];
		for (int i=0; i<=n; i++) {
			if (i == 0) {
				memo[i] = a1;
			}
			else if (i == 1) {
				memo[i] = a2;
			}
			else {
				memo[i] = memo[i-1] + memo[i-2];
			}
		}
		System.out.print(memo[n]+" ");
	}
}
