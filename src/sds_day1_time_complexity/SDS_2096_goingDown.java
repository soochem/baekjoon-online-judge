package sds_day1_time_complexity;

import java.util.*;
import java.io.*;

public class SDS_2096_goingDown {
	public static void main(String[] args) throws Exception {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int[][] dpmax = new int[n][3];
		int[][] map = new int[n][3];
		int max = 0; int min = 10;
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dpmax[0][0] = map[0][0];
		dpmax[0][1] = map[0][1];
		dpmax[0][2] = map[0][2];
		
		for (int i=1; i<n; i++) {
			max = Math.max(dpmax[i-1][0], dpmax[i-1][1]);
			dpmax[i][0] = max + map[i][0];
			dpmax[i][1] = Math.max(max, dpmax[i-1][2]) + map[i][1];
			dpmax[i][2] = Math.max(dpmax[i-1][1], dpmax[i-1][2]) + map[i][2];
		}
		
		max = 0;
		
		for (int i=0; i<n; i++) {
			if (max < dpmax[n-1][i]) max = dpmax[n-1][i];
//			if (min > dpmin[n-1][i]) min = dpmin[n-1][i];
		}
		
		for (int i=1; i<n; i++) {
			min = Math.min(dpmax[i-1][0], dpmax[i-1][1]);
			dpmax[i][0] = min + map[i][0];
			dpmax[i][1] = Math.min(min, dpmax[i-1][2]) + map[i][1];
			dpmax[i][2] = Math.min(dpmax[i-1][1], dpmax[i-1][2]) + map[i][2];
		}

		min = Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			if (min > dpmax[n-1][i]) min = dpmax[n-1][i];
		}

		bw.write(String.valueOf(max)+" "+String.valueOf(min));
		bw.flush();
		bw.close();
	}
}
