package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Sam_16637_dp {
	static int n, len;
	static String s;
	
	static int calc (int x, int y, char c) {
		switch (c) {
			case '+':
				return x+y;
			case '-':
				return x-y;
			case '*':
				return x*y;
		}
		return 0;	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		len = n/2+1;
		
		int[] iArr = new int[len+1];
		char[] cArr = new char[len+1];
//		int[][] dp = new int[n+1][n+1]; // i~j까지 최댓값
		int[][] dp = new int[n+1][2]; // i까지 최댓값
		
		s = br.readLine();
			
		for (int i=0; i<n; i++) { // 두자리 수 이상은 이렇게 하면 x
			if (i%2 == 0) iArr[i/2] = s.charAt(i) - '0';
			else cArr[i/2] = s.charAt(i);
		}
		
		dp[0][0] = iArr[0];
		dp[1][1] = calc(iArr[0], iArr[1], cArr[0]);
		dp[1][0] = dp[1][1];
		
		for (int i=2; i<len; i++) {  // 연산자는 한개만
			dp[i][1] = calc(Math.max(dp[i-2][0], dp[i-2][1]),
					calc(iArr[i-1], iArr[i], cArr[i-1]), cArr[i-2]);
			dp[i][0] = Math.max(calc(dp[i-1][1], iArr[i], cArr[i-1]),
					calc(dp[i-1][0], iArr[i], cArr[i-1]));
		}
		
		int ans = Math.max(dp[len-1][0], dp[len-1][1]);
		bw.write(String.valueOf(ans));
		bw.write("\n");
		
		bw.flush();
		bw.close();
	}
}
