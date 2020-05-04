package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_9093 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int tc, i, j;
		String[] s;
		
		tc = Integer.parseInt(br.readLine());
		
		for (j = 0; j < tc; j++) {
			s = br.readLine().split(" ");
			for (i = 0; i < s.length; i++) {
				sb.setLength(0);
				sb.append(s[i]);
				bw.write(sb.reverse().toString());
				bw.write(" ");
			}
			bw.write("\n");
		}
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
