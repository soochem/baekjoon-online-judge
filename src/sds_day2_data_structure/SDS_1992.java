package sds_day2_data_structure;

import java.io.*;
import java.util.*;

public class SDS_1992 {
	public static int n;
	public static char[] left, right;
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int a; char b, c;
		String s;
		
		n = Integer.parseInt(br.readLine());
		left = new char[n+1];
		right = new char[n+1];
		
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		for (int i=0; i<n; i++) adjList.add(new ArrayList<Integer>());
		
		for (int i=0; i<n; i++) {
			s = br.readLine();
			a = s.charAt(0)-'A';
			b = s.charAt(2);
			c = s.charAt(4);
			if (b != '.') left[a] = b;
			if (c != '.') right[a] = c;
		}
//		for (int bb : left) System.out.print((char)(bb+'A'));
//		System.out.println();
		
		preorder('A');
		bw.write("\n");
		inorder('A');
		bw.write("\n");
		postorder('A');
		bw.write("\n");
		
		bw.flush(); bw.close();
	}
	
	private static void preorder(char c) throws Exception {
		int idx = c - 'A';
		bw.write(c);
		if (left[idx] != 0) preorder(left[idx]);
		if (right[idx] != 0) preorder(right[idx]);
	}
	
	private static void inorder(char c) throws Exception {
		int idx = c - 'A';
		if (left[idx] != 0) inorder(left[idx]);
		bw.write(c);
		if (right[idx] != 0) inorder(right[idx]);
	}
	
	private static void postorder(char c) throws Exception {
		int idx = c - 'A';
		if (left[idx] != 0) postorder(left[idx]);
		if (right[idx] != 0) postorder(right[idx]);
		bw.write(c);
	}
}
