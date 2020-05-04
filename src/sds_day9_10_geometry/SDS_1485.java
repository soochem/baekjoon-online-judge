package sds_day9_10_geometry;

import java.util.*;
import java.io.*;

public class SDS_1485 {
	static int test;
	static int[][] point;
	
	static long dist (int[] i, int[] j) {
		return (long)((i[0]-j[0])*(i[0]-j[0])) + (long)((i[1]-j[1])*(i[1]-j[1])); 
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		test = Integer.parseInt(br.readLine());
		
		while (--test >= 0) {
			
			point = new int[4][2];
			
			for (int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
				point[i][0] = Integer.parseInt(st.nextToken());
				point[i][1] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(point, new Comparator<int[]>() {
				public int compare (int[] a, int[] b) {
					return (a[0] > b[0]) ? 1 : (a[0] == b[0]) ? ((a[1] > b[1]) ? 1 : -1): -1;
				}
			});
			// 비교 연산이 잘못된 거 였음
			
			long[][] d = new long[4][4];
			for (int i=0; i<4; i++) {
				for (int j=0; j<4; j++) {
					d[i][j] = dist(point[i], point[j]);
//					System.out.print(d[i][j]+" ");
				}
//				System.out.println();
			}
			
//			for (int[] a: point) {
//				for (int b:a)
//				System.out.print(b+" ");
//				System.out.println();
//			}
//			
			boolean flag = false;
			
			if (d[0][2] + d[0][1] == d[1][2]
					&& d[0][2] == d[2][3] && d[0][1] == d[0][2] && d[2][3] == d[1][3]) {
				flag = true;
			}
//			System.out.println(point[0][0]+ " " +point[0][1]+" "+point[2][0]+" "+point[2][1]);
//			System.out.println(d[0][1] +" "+d[0][2]+" "+d[2][3]);
			
			if (flag) bw.write(String.valueOf(1));
			else bw.write(String.valueOf(0));
			bw.write("\n");
		}
		
		bw.flush(); bw.close();
	}

}

