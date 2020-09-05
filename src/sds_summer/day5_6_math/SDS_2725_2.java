// 보이는 점의 개수
// 최대공약수로 구하기?

package sds_summer.day5_6_math;

import java.io.*;
import java.util.*;


public class SDS_2725_2 {
	static int max = 1000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int testcase;
		int n;
//		boolean[][] visited;
//		int[][] visited;
		int ans = 0; 	
		int[] ansArr;
//		visited = new int [max+1][max+1];
		ansArr = new int [max+1];
		
		testcase = Integer.parseInt(br.readLine());
//		int gcdVal = 0;
		n = max;
		ansArr[1] = 1; //(1,1), 0부터 할 필요 없어짐
		
		for (int i=1; i<=n; i++) {
			for (int j=i; j<=n; j++) {
//				if (i==0 && j==0) continue;

//				gcdVal = gcd(i, j);
//				if(visited[i/gcdVal][j/gcdVal] == 0) { // 중복해서 세개 됨?
//					visited[i/gcdVal][j/gcdVal] = ++ans;			
//				}
				
				if (gcd(j, i) == 1) { // 무조건 j가 큼
					ansArr[j]+=2;
//					System.out.println(i+" "+j+" "+ansArr[j]);
				}
			}
			ansArr[i] += ansArr[i-1]; // 한줄씩 더함
		}
		
//		for (int a:ansArr) System.out.print(a+" ");
		
		while (--testcase >= 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			bw.write(String.valueOf(ansArr[n]));
			bw.write("\n");
		}
		
		bw.flush(); bw.close();
	}
	
	private static int gcd(int a, int b) {
		int r = 0;
//		if (a < b) {
//			r = a;
//			a = b;
//			b = r;
//		}
		
		while (b != 0) {
			r = a%b;
			a = b;
			b = r;
		}
		
		return a;
	}
}
