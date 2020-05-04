package string;

import java.io.*;

public class BOJ_14444_longPal {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int l, i, j, ans;
		String s;
		int[][] dp;
		
		s = br.readLine();
		l = s.length();
		ans = 0;
		dp = new int[l][l];
		
		//dp 로 해결하기
		// 1. dp[i][i] = 1
		// 2. dp[i-1][i+1] = dp[i][i]+2;
		for (i = 0; i < l; i++) {
			dp[i][i] = 1;
		}
		for (i = 0; i < l; i++) {
			for (j = 1; i+j < l; j++) {
				if (i-j < 0) break;
				if (s.charAt(i-j) == s.charAt(i+j)) {
					dp[i-j][i+j] = Math.max(dp[i-j][i+j], dp[i-j+1][i+j-1]+2);
					if (ans < dp[i-j][i+j]) ans = dp[i-j][i+j];
				}
				else {
					break;
				}
			}
			
			for (j = 1; i+j < l; j++) {
				if (i-j < -1) break;
				if (s.charAt(i-j+1) == s.charAt(i+j)) {
					dp[i-j+1][i+j] = Math.max(dp[i-j+1][i+j], dp[i-j+2][i+j-1]+2);
					if (ans < dp[i-j+1][i+j]) ans = dp[i-j+1][i+j];
				}
				else {
					break;
				}
			}
		}
		
		System.out.println(ans);
	}
}
