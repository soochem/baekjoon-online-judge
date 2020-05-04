package string;

import java.io.*;

public class BOJ_14444_longPal3 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int l, i, j, ans, cnt;
		String s;
		
		s = br.readLine();
		l = s.length();
		ans = 0;
		cnt = 0;

		for (i = 0; i < l; i++) {
			for (j = 1; i+j < l; j++) {
				cnt = 1;
//				if (i-j < 0) break;
				if (s.charAt(i-j) == s.charAt(i+j)) {
					cnt+=2;
					if (ans < cnt) ans = cnt;
				}
				else {
					break;
				}
			}
			
			for (j = 1; i+j < l; j++) {
				cnt = 1;
				if (i-j < -1) break;
				if (s.charAt(i-j+1) == s.charAt(i+j)) {
					cnt+=2;
					if (ans < cnt) ans = cnt;
				}
				else {
					break;
				}
			}
		}
		
		System.out.println(ans);
	}
}
