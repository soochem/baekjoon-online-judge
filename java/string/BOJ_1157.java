package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1157 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s;
		int i, j, num, max, idx;
		int[] cnt = new int[26];
		
		s = br.readLine();
		num = 0;
		max = -1;
		idx = 0;
		
		for (i=0; i<s.length(); i++) {
			j = s.charAt(i);
			j = (j > 95)? j-'a':j-'A';
			cnt[j]++;
		}
		
		for (i=0; i<26; i++) {
			if (max < cnt[i]) {
				num = 1;
				max = cnt[i];
				idx = i;
			}
			else if (max == cnt[i]) num++;
		}
		
		if (num == 1) bw.write((char)(idx+'A'));
		else bw.write("?");
		bw.flush();
		bw.close();
	}
}
