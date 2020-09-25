package sds_summer.day2_data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 뒤에서부터 앞자리
// 숫자 / 문자 구분 - 빼줘야하는 숫자가 다르다.
public class SDS_2745_bexpression {
	static int b, ans;
	static String n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] in = br.readLine().split(" ");
		
		n = in[0];
		b = Integer.parseInt(in[1]);
		
		ans = 0;
		int cmp = 'A'-10;
		int div = 1;
		for (int i=n.length()-1; i>=0; i--) {
			char c = n.charAt(i);
			if (c >= 'A') ans += (c-cmp)*div;
			else ans += (c-'0')*div;
//			System.out.println((c-'0')+ " " +ans);
			div *= b;
		}
		
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}
}
