// 내려가기
// 1. 런타임 에러 dp 를 쓰더라도 n 크기가 중요함
// 예시 4 byte * 300,000 = 1,200,000 Byte = 1,172 KByte = 1.14 MByte
// 2. bottom-up은 받으면서 동시에 가능하다

package sds_summer.day1_time_complexity;

import java.util.*;
import java.io.*;

public class SDS_2096_goingDown_2 {
	public static void main(String[] args) throws Exception {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		//int[][] dpmax = new int[n][3];  // runtime error 10^5 *3 *3
		//int[][] map = new int[n][3];
		int max = 0; int min = 0;
		int a,b,c; a=b=c=0;
		int max0, max1, max2; max0=max1=max2=0;
		int min0, min1, min2; min0=min1=min2=0;
		int temp0, temp1, temp2; temp0=temp1=temp2=0;
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			if (i !=0) {
				temp0 = Math.max(max0, max1);
				temp1 = Math.max(temp0, max2);
				temp2 = Math.max(max1, max2);
			}
			
			max0 = temp0 + a;
			max1 = temp1 + b;
			max2 = temp2 + c;
			
			if (i !=0) {
				temp0 = Math.min(min0, min1);
				temp1 = Math.min(temp0, min2);
				temp2 = Math.min(min1, min2);
			}
			
			min0 = temp0 + a;
			min1 = temp1 + b;
			min2 = temp2 + c;
			
			if (i == n-1) {
				max = Math.max(Math.max(max0, max1), max2);
				min = Math.min(Math.min(min0, min1), min2);
			}
		}

		bw.write(String.valueOf(max)+" "+String.valueOf(min));
		bw.flush();
		bw.close();
	}
}
