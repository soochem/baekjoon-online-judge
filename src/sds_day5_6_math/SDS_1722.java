package sds_day5_6_math;

import java.util.*;
import java.io.*;

public class SDS_1722 {
	static int n, k;
	static long m;
	static int[] map;
	static long[] fac;
	static boolean[] visited;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static long perm2ord () {
		long ans = 0;
		
		for (int i=n; i>=1; i--) {  //남은 문자열 개수
			int num = 0;
			for (int j=1; j<=n; j++) {  
				if (!visited[j]) {
					if (map[n-i+1] == j) {
						// 방문 안한 새 숫자
						visited[j] = true;
//						visited[n-i+1] = true;
						ans += fac[i-1]*num;
						break;
					}
					num++;
				}
			}
		}
		
		return ans+1;
	}
	
	static int ord2perm (long x) throws IOException {
		for (int i=n; i>=1; i--) {  //1~n자리
			int num = 0;
			for (int j=1; j<=n; j++) {
				if (!visited[j]) {
					num++; // 아직 방문 안한애, 키워서 기준 넘게 만들것
					if (fac[i-1]*num >= m) {
						visited[j] = true;
						m -= fac[i-1]*(num-1);
						bw.write(String.valueOf(j)+" ");
						break;
					}
				}
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());  // 쿼리
		
		fac = new long[n+1];
		fac[0] = 1;
		for (int i=1; i<=n; i++) {
			fac[i] = fac[i-1]*i;
		}
		
		// n 개의 수로 이루어진 m 번째 수열
		// 첫번째 : m/(n-1)!
		// 나머지 (n-1개) : m%(n-1)!
		map = new int[n+1];
		visited = new boolean[n+1];
		
		if (k == 1) {
		// 순서 -> 순열 
			m = Long.parseLong(st.nextToken());
			ord2perm(m);
		}
		else {
		// 순열 -> 순서
			for (int i=1; i<=n; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			bw.write(String.valueOf(perm2ord()));
		}
		
		bw.write("\n");
		bw.flush(); bw.close();
	}
}
