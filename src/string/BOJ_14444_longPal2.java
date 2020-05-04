package string;

import java.io.*;

public class BOJ_14444_longPal2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int l, i, j, ans;
		String s;
		int[][] dp;  // 메모리 초과!!!!!!!!
		
		s = br.readLine();
		l = s.length();
		ans = 1;
		dp = new int[l][l];
		
		//dp 로 해결하기
		// 1. dp[i][i] = 1
		// 2. dp[i-1][i+1] = dp[i][i]+2; -- x
		// 3. dp[i][j] = dp[i+1][j]+2;
		for (i = 0; i < l; i++) dp[i][i] = 1;
		for (i = 0; i < l-1; i++) if(s.charAt(i) == s.charAt(i+1)) {
			ans = 2;
			dp[i][i+1] = 2;
		}
		
		for (j = 2; j < l; j++) {  // j가 먼저 매우 중요!
			for (i = 0; i+j < l; i++) {
//				System.out.println("i "+i+" "+(i+j)+" "+dp[i+1][i+j-1]);
				if (s.charAt(i) == s.charAt(i+j) && dp[i+1][i+j-1] != 0) {
					dp[i][i+j] = Math.max(dp[i][i+j], dp[i+1][i+j-1]+2);
					if (ans < dp[i][i+j]) ans = dp[i][i+j];
				}
			}
		}
		
		System.out.println(ans);
	}
}
