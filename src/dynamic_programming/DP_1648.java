// 격자판 채우기
package dynamic_programming;

import java.util.*;
import java.io.*;

public class DP_1648 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
	}
	private static int fib(int x) {
		int[] fib = new int[x+1];
		fib[1] = 1; fib[2] = 2;
		if(x > 3) {
			for (int i=3; i<=x; i++) {
				fib[i] = fib[i-1] + fib[i-2];
			}
		}
		return fib[x];
	}
//	private static int ffib(int x) {
//		int[] ffib = new int[x+1];
//		
//		for(int i=3; i<=x; i++) {
//			int f = fib(i/4);
//			if ((i/2)%2 == 0) {
//				ffib[i] = f*f+f;
//			}
//			else {
//				ffib[i] = f*f+f-1;
//			}
//		}
//		return ffib[x];
//	}
}
