package sds_day7_8_dp;

import java.io.*;
//import java.util.*;

public class SDS_5582 {
	static String s1, s2;
	static char c1, c2;
	static int l1, l2;
	static int len;
	static int[][] d;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringTokenizer st;

		s1 = br.readLine();
		s2 = br.readLine();
		len = 0;
		l1 = s1.length();
		l2 = s2.length();
		d = new int[l1+1][l2+1];
		
		// d[i][j] : 1번째 문자열의 1~i까지 검사했을때 + 1~j까지 검사했을 때 일치하는
		// 것 중 최장 길이
		
		int max = 0;
		
//		if (s1.charAt(0) == s2.charAt(0)) d[0][0] = 1;
//		if (s1.charAt(0) == s2.charAt(1)) d[0][1] = 1;
		
		for (int i=0; i<l1; i++) {
			c1 = s1.charAt(i);
			for (int j=0; j<l2; j++) {
				c2 = s2.charAt(j);
//				System.out.println(c1 +" "+c2);
				if (c1 == c2) {
					if (d[i+1][j+1] < d[i][j]+1)
						d[i+1][j+1] = d[i][j]+1;
				}
				if (d[i+1][j+1] > max) max = d[i+1][j+1];
			}
		}

		
		bw.write(String.valueOf(max));
		bw.write("\n");
		bw.flush(); bw.close();
	}
}
