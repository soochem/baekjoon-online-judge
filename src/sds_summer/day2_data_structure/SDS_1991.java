package sds_summer.day2_data_structure;

import java.io.*;
import java.util.*;

public class SDS_1991 {
	public static int n, len;
	public static int[] bitree;
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int a, b, c;
		String s;
		
		n = Integer.parseInt(br.readLine());
		len = (int)Math.pow(2, n) -1;
		bitree = new int[len];
		
		Arrays.fill(bitree, -1);
		bitree[0] = 0;
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		for (int i=0; i<n; i++) adjList.add(new ArrayList<Integer>());
		
		for (int i=0; i<n; i++) {
			s = br.readLine();
			a = s.charAt(0) - 'A';
			b = s.charAt(2) - 'A';
			c = s.charAt(4) - 'A';
			if (b >= 0) bitree[a*2+1] = b;
			if (c >= 0) bitree[a*2+2] = c;
		}
		for (int bb : bitree) System.out.print((char)(bb+'A'));
		System.out.println();
		
		preorder(0);
		bw.write("\n");
		
		bw.flush(); bw.close();
	}
	
	private static void preorder(int idx) throws Exception {
//		if (idx >= len/2) {
//			return;
//		}

		bw.write((char)(bitree[idx]+'A'));

		if (idx*2+1 < len) {
			if (bitree[idx*2+1] != -1) {
				System.out.println((char)(bitree[idx*2+1]+'A'));
				preorder(idx*2+1);
			}
		}
		
		if (idx*2+2 < len) {
			if (bitree[idx*2+2] != -1) {
				System.out.println((char)(bitree[idx*2+2]+'A'));
				preorder(idx*2+2);
			}
		}
		System.out.println();
	}
}
