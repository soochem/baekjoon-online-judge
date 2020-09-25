package string;

import java.io.*;

public class BOJ_5622 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
//		StringBuilder sb;
		String s;
		int i, l, ans, tmp;
//		int[] arr = new int[2];
		
		s = br.readLine();
		l = s.length();
		ans = 0;
		
		for (i=0; i<l; i++) {
			if (s.charAt(i) == 'S') tmp = 7;
			else if (s.charAt(i) == 'V') tmp = 8;
			else {
				tmp = (s.charAt(i)-'A')/3 + 2;
				if (tmp > 9) tmp = 9;
			}
			System.out.println(tmp);
			ans += tmp;
		}
		
		bw.write(String.valueOf(ans+l));
		bw.flush();
		bw.close();
	}
}
