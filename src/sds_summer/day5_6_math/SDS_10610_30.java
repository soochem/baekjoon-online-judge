package sds_summer.day5_6_math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class SDS_10610_30 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		
		String s;
		Integer[] map;
		boolean flag = false;
		
		s = br.readLine();
		map = new Integer[s.length()];
		
		int sum = 0;
		for (int i=0; i<s.length(); i++) {
			int c = s.charAt(i)-'0'; 
			if (c == 0) {
				flag = true;
			}
			map[i] = c;
			sum += c;
		}
		
		Arrays.sort(map, Comparator.reverseOrder());
		
		if (flag) {
			if (sum%3 == 0) {
				for (int m: map) bw.write(String.valueOf(m));
			}
			else bw.write(String.valueOf(-1));
		}
		else bw.write(String.valueOf(-1));
		
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
