// counter example
//2 6
//8 8

//4 2
//1 1 1 1 (답을 저장해야함)

package sds_day1_time_complexity;

import java.util.*;
import java.io.*;

public class SDS_2805_treeCutting {
	public static void main(String[] args) throws Exception {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 나무수 
		int m = Integer.parseInt(st.nextToken()); // 필요한 길이
		int[] map = new int[n];

		int min, max;
		max = 0;
		min = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		
		// 조금이라도 짧은 범위를 보려고
		for (int i=0; i<n; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			if (max < map[i]) max = map[i];
			else if (min > map[i]) min = map[i];
		}
		
		Arrays.sort(map);  // 이분탐색하려고
		int cut; 
		long sum;  // 10^6 * 2*10^9
		int ans=0;
		
		min = (min-m > 0)? min-m : 0;
		//mid = 0;
		
		while (min <= max) {
			//mid = (max+min)/2;
			sum = 0;
			cut = (max+min)/2;
			for (int i=0; i<n; i++) {
				if (map[i] > cut) sum += map[i]-cut;
			}
			
			if (sum >= m) {
				min = cut+1;  // 남으면? -> 높이기/ 더필요? -> 낮추기
				ans = cut;
				if (sum == m) {
					break;
				}
			}
			
			else max = cut-1; // mid는?
			//System.out.println(mid);
			//System.out.println(sum);
		}
		
		bw.write(ans+"\n");
		bw.flush(); bw.close();
	}
}
