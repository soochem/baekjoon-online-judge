// 보이는 점의 개수

package sds_day5_6_math;

import java.io.*;
import java.util.*;

public class SDS_2725 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int testcase;
		int n;
		boolean[][] visited;
		int ans = 0; 
		
//		n = 1000;
		visited = new boolean [1001][1001];
		
		
//		for (int i=0; i<=n; i++) {
//			for (int j=0; j<=n; j++) {
//				if (i == 0 && j == 0) continue;
//				if (i == j) {
//					System.out.println(i+" "+j);
//					ansArr[i] = ans;
//				}
//
//				if (!visited[i][j]) {
//					ans++;
//					for (int k=2; k*j<=n && k*i<=n; k++) {
//						if (!visited[k*i][k*j]) {
////							System.out.println(k*i+" "+k*j);
//							visited[k*i][k*j] = true;
//						}
//					}
//				}
//			}
//		}
		
		
		testcase = Integer.parseInt(br.readLine());
		
		// 그때 그때 구한다고 하면 
		while (--testcase >= 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			ans = 0;
			
			for (int i=0; i<=n; i++) {
				for (int j=0; j<=n; j++) {
					if (i == 0 && j == 0) continue;
					if (!visited[i][j]) {
						ans++;
						for (int k=2; k*j<=n && k*i<=n; k++) {
							if (!visited[k*i][k*j]) {
								visited[k*i][k*j] = true;
							}
						}
					}
				}
			}
			
			bw.write(String.valueOf(ans));
			bw.write("\n");
		}
		
		bw.flush(); bw.close();
	}
}
