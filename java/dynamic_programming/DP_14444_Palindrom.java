package dynamic_programming;

import java.io.*;

public class DP_14444_Palindrom {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int i, j, len, max;
		char[] input = new char[(int)1e7];
		int[] dp = new int[(int)1e7];

		String s = br.readLine();
		max = 0;
		
		j = 0;
		for (i = 0; i < s.length(); i++) {
			input[j] = '#';
			input[j+1] = s.charAt(i);
			j+=2;
		}
		input[j] = '#';
		len = j;
		
//		for (i = 0; i < 20; i++) {
//			System.out.print(input[i]+" ");
//		} System.out.println();
		
		// dp
		for (i = 0; i < len; i++) {
			j = dp[i];  // 반지름
			while (true) {
				if (j > i) break;
				if (input[i-j] == input[i+j]) {
					dp[i]++;
					dp[i+j] = Math.min(dp[i-j], dp[i]-j); // 복사
				}
				else break;
				j++;
			}
			
//			// 복사 (시간 단축)
//			for (j = 1; j < dp[i]; j++) {
//				dp[i+j] = Math.min(dp[i-j], dp[i]-j);
//			}

//			for (j = 0; j < 20; j++) {
//				if (dp[j] == 0) break;
//				System.out.print(dp[j]+" ");
//			} System.out.println();
			max = (max < dp[i])? dp[i] : max;
		}
		
		System.out.println(max-1);
		// nananan
		// nananxx
	}
}
