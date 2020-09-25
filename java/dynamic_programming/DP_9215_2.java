// LCS 최장 공통 부분 수열
package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_9215_2 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		int l1 = s1.length();
		int l2 = s2.length();
		int[][] memo = new int [l1+1][l2+1];
		int i=0;
		while (i < l1) {
			char c = s1.charAt(i);
			for (int j=0; j<l2; j++) {
				if (c == s2.charAt(j)) {
					memo[i+1][j+1] = memo[i][j]+1;
				}
				else {
					memo[i+1][j+1] = Math.max(memo[i+1][j], memo[i][j+1]);
				}
			}
			i++;
		}
		System.out.println(memo[l1][l2]);
	}
}
