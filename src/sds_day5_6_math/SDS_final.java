package sds_day5_6_math;

import java.io.*;
import java.util.*;

public class SDS_final {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int h, w, n;
		int ans;
		
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		int[][][][] d = new int[2][3][h+1][w+1];  // 방향, 방향 튼거
		// 방향 왼: 0/ 방향 위:1
		// 방향 튼 횟수: 0~2
		
		for (int i=0; i<h; i++) d[1][0][i][0] = 1;
		for (int i=0; i<w; i++) d[0][0][0][i] = 1;
		
		for (int i=1; i<h; i++) {
			for (int j=1; j<w; j++) {
				// 방향 왼
				d[0][0][i][j] += (d[0][1][i][j-1] + d[0][0][i][j-1]);
				d[0][1][i][j] = d[0][0][i][j-1]+1;
				d[0][2][i][j] = d[0][1][i][j-1]+1;
				
				// 방향 위
				d[1][0][i][j] += (d[1][1][i-1][j] + d[1][0][i-1][j]);
				d[1][1][i][j] = d[1][0][i-1][j]+1;
				d[1][2][i][j] = d[1][1][i-1][j]+1;
			}
		}
		
		ans = d[1][0][h-1][w-1]+d[1][1][h-1][w-1]
			+d[0][0][h-1][w-1]+d[0][1][h-1][w-1];
		
		bw.write(String.valueOf(ans));
		bw.write("\n");
		bw.flush(); bw.close();
	}
}

