package sds_summer.day2_data_structure;

import java.util.*;
import java.io.*;


public class SDS_2143_arraySum {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int t, n, m;
		int[] a, sa, b, sb;  //s:부배열합
		
		t = Integer.parseInt(br.readLine());  // target
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		sa = new int[(n+1)*(n+1)];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		m = Integer.parseInt(br.readLine());
		b = new int[m];
		sb = new int[(m+1)*(m+1)];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		//////// 알고리즘 /////////
		int left, right, mid; left = right = mid = 0;
		// t보다 작은 부배열합
		int sum = a[0];
		int idxa = 0;
		Arrays.fill(sa, Integer.MAX_VALUE);
		Arrays.fill(sb, Integer.MAX_VALUE);

		while (left <= right) {
			if (sum >= t) {
				sum -= a[left++];
			}
			else {
				sa[idxa++] = sum;
//				System.out.println(sum);
				if (right == n-1) { // 끝일 때
					//System.out.println("l " +left+" r "+right);
					//인덱스 에러
					sum -= a[left++];
				}
				else {
					right++;
					sum += a[right];
				}
			}
		}
		
		left = right = sum = 0;
		int idxb = 0;
		sum = b[0];
		
		while (left <= right) {
			if (sum >= t) {
				sum -= b[left++];
			}
			else {
				sb[idxb++] = sum;
				System.out.println(left+" "+right);
				System.out.println(sum);
				if (right == m-1) { // 끝일 때
					sum -= b[left++];
				}
				else {
					right++;
					sum += b[right];
				}
			}
		}
		
		Arrays.sort(sa);  // 이진탐색?
		Arrays.sort(sb);
		for (int i=0; i<idxb; i++) {
			System.out.print(sb[i]+" ");
		}
		System.out.println();
		
		int diff = 0;
		int cnt = 0;  // 답
		int ansIdx;
		
		for (int i=0; i<idxa; i++) {  // a 기준으로 움직이며 b랑 합쳐셔 t 되는 모든 경우 구하기
			diff = t-sa[i];  // diff 찾기
			System.out.println("?"+diff);
			
			left = 0; right = idxb-1;
			ansIdx = -1;
			
			while (left <= right) {
				mid = (left+right)/2;
				if (diff == sb[mid]) {
					ansIdx = mid;
					break;
				}
				else if (diff < sb[mid]) right = mid-1;
				else left = mid+1;
			}
			System.out.println("ans "+ansIdx);
		}		
		
		bw.flush(); bw.close();
	}
}