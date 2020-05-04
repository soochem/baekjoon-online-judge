package string;

import java.io.*;

public class BOJ_2908 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb;
		String[] s;
		int i, max;
		int[] arr = new int[2];
		
		s = br.readLine().split(" ");
		
		for (i=0; i<2; i++) {
			sb = new StringBuilder();
			sb.append(s[i]);
			sb.reverse();
			arr[i] = Integer.parseInt(sb.toString());
		}
		sb = new StringBuilder();
		sb.append(max = (arr[0] > arr[1]) ? arr[0]:arr[1]);
		System.out.println(sb.toString());		
	}
}
