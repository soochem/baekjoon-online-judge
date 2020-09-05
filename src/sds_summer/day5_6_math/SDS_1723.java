package sds_summer.day5_6_math;

import java.util.*;
import java.io.*;

public class SDS_1723 {
	public static int n;
	public static long m, k;
	public static int[] map;
	public static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());  // 쿼리
//		m = Long.parseLong(st.nextToken());
		
		long[] fac = new long[n+1];
		fac[0] = 1;
		for (int i=1; i<=n; i++) {
			fac[i] = fac[i-1]*i;
		}
		
		// n 개의 수로 이루어진 m 번째 수열
		// 첫번째 : m/(n-1)!
		// 나머지 (n-1개) : m%(n-1)!
		
		int size = n;
		visited = new boolean[n+1];
		int[] map = new int[n+1]; // 순서
		
		
		if (k == 1) {
			m = Long.parseLong(st.nextToken());

			while (--n >= 0) {
				if (n == 0) {
					for (int j=1; j<=size; j++) {
						if (!visited[j]) {
							bw.write(j+" ");
							break;
						}
					}
				}
				else {
					for (int i=1; i<=size; i++)	{
						if (visited[i]) continue;
						
						// 안지나간 수 i
						if (fac[n] >= m) {
							bw.write(i+" ");
							visited[i] = true;
							break;
						}
						else {
							m -= fac[n];
						}
//						break;
					}
				}
			}
		}
		else {
			for (int i=1; i<=n; i++) map[i] = Integer.parseInt(st.nextToken());
			int cnt = 0;
			int tmp_cnt = 0;
			// cnt: 순서 , 답
			// map: 저장된 입력값
			
			while (--n >= 0) {
				tmp_cnt = 0;
				
				// 종료조건?
				// 
				for (int i=1; i<=size; i++)	{
					tmp_cnt++;
					if (visited[i]) continue;     
					// 안지나간 수 i
					if (i == map[cnt]) {
						bw.write("case1 "+i+" ");
						visited[i] = true;
						cnt += tmp_cnt;
						break;
					}
					else {
						cnt += fac[n-1];
					}
				}
			}
			
			bw.write(String.valueOf(cnt));
			
		}
		
		bw.write("\n");
		bw.flush(); bw.close();
	}
}
