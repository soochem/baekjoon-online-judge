package sds_summer.day5_6_math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SDS_1934_gcf {
	static int a, b;
	
	static int gcd (int a, int b) {
		if (b == 0) return a;
		return gcd(b, a%b);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		
		int testcase = Integer.parseInt(br.readLine());

		while (--testcase >= 0) {
			String[] s = br.readLine().split(" ");
			a = Integer.parseInt(s[0]);
			b = Integer.parseInt(s[1]);
			
			int max, min;
			max = (a > b)? a : b;
			min = (max == a)? b : a;
			int g = gcd(max, min);
			bw.write(String.valueOf(a*b/g));
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}
