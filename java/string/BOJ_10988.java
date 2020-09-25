package string;

import java.io.*;

public class BOJ_10988 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringBuilder sb;
		
		String s;
		int i, l, ans;
		boolean flag;
		
		s = br.readLine();
		l = s.length();
		ans = 0;
		flag = true;
		
		for (i=0; i<=l/2; i++) {
			if (s.charAt(i) != s.charAt(l-i-1)) {
				flag = false;
				break;
			}
		}
		
		bw.write(String.valueOf(ans = (flag)? 1:0));
		bw.flush();
		bw.close();
	}
}
