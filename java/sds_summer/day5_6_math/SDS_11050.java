package sds_summer.day5_6_math;

import java.io.*;
import java.util.*;

public class SDS_11050 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n, k;
		int[] fac;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		fac = new int[n+1];
		fac[0] = 1;
		fac[1] = 1;
		for (int i=2; i<=n; i++) {
			fac[i] = fac[i-1] * i;
		}
		
		// 이항계수
		// nCk = n!/k!*(n-k)!
		int ans = fac[n]/(fac[k]*fac[n-k]);
		bw.write(String.valueOf(ans));
		bw.write("\n");
		bw.flush(); bw.close();
	}
}
