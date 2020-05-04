// LCS 최장 공통 부분 수열
package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_9215 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		int dp=0;
		
		int i=0; int idx = i;
		while (i<s1.length()) {
			char c = s1.charAt(i);
			System.out.println(c+" "+i+" "+idx);
			if (idx < s2.length()) {
				if (c == s2.charAt(idx)) {
					System.out.println(c);
					dp++;
					i++;
				}
				idx++;
			}
			else {
				i++;
				idx--;
			}
			System.out.println(i+" "+idx);

			System.out.println();
		}
		System.out.println(dp);
	}
}
