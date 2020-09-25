package string;

import java.util.*;
import java.io.*;

public class BOJ_1316_groupChecker {
	static boolean groupChk(String s) {
		boolean[] chk = new boolean[26];
		boolean flag;
		int l, i;
		char c;
		
		flag = true;
		l = s.length();
		chk[s.charAt(0)-'a'] = true;
		
		for (i = 1; i < l; i++) {
			c = s.charAt(i);
			if (c != s.charAt(i-1)) {
				c -= 'a';
				if (chk[c]) {// 이미 지나온 거
					flag = false;
					break;
				}
				else chk[c] = true;
			}
		}
		
		return flag;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, i, cnt;
		String s;
		
		n = Integer.parseInt(br.readLine());
		cnt = 0;
		
		for (i = 0; i < n; i++) {
			s = br.readLine();
			if (groupChk(s)) cnt++;
		}
		
		System.out.println(cnt);
	}
}
