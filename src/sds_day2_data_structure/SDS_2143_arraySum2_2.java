package sds_day2_data_structure;

import java.util.*;
import java.io.*;


public class SDS_2143_arraySum2_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		long t;
		int n, m;
		int[] a, b;
		
		t = Long.parseLong(br.readLine());  // target
		
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		m = Integer.parseInt(br.readLine());
		b = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		//////// 알고리즘 /////////
		// t보다 작은 부배열합
		long sum = 0;
		ArrayList<Long> sa = new ArrayList<>();
		for (int i=0; i<n; i++) {
			sum = 0;
			for (int j=i; j<n; j++) {
				sum+=a[j];
//				if (sum < t) { // 음수가 나올 수 있음
//					sa.add(sum);
//				}
//				else break;
				sa.add(sum);
			}
		}
		
		ArrayList<Long> sb = new ArrayList<>();
		sum = 0;
		for (int i=0; i<m; i++) {
			sum = 0;
			for (int j=i; j<m; j++) {
				sum+=b[j];
				sb.add(sum);
			}
		}
		
		Collections.sort(sa);
		Collections.sort(sb);
//		System.out.println(sa);
//		System.out.println(sb);
		
		// 이진탐색?
		long diff;
		long cnt = 0;  // 답
		int left, right, mid; right = sb.size()-1;
		long oper1; int oper2;
		
		for (int i=0; i<sa.size(); i++) {
			diff = t-sa.get(i);
			left = mid = 0;
			oper1 = 1; // 갯수대로 곱할거
			oper2 = 0;
			
			while (i < sa.size()-1 && sa.get(i) == sa.get(i+1)) {
				i++;
				oper1++;
			}
			
			while (left < right) {
//				System.out.println(left+" "+right);
				mid = (left + right)/2;
				if (sb.get(mid) >= diff) {
					// 이상인 값이 먼저 나오도록 (lower bound)
					// 초과하는 값이 먼저 나오도록  (upper bound)
					right = mid;
				}
				else {
					left = mid+1;
				}
			}
			
			// 결과는 right (이상인 값 중에 가장 낮은 idx, 작은 범위 중에 가장 큰 값)
			// right를 움직이면 찾을 때 속도가 느려짐
//			System.out.println(right);
			
			while (right+oper2 < sb.size() && sb.get(right+oper2) == diff) {
//				right++;
				oper2++;
			}
			
			cnt += oper1*oper2;
		}
		
		
		bw.write(String.valueOf(cnt));
		bw.write("\n");
		
		bw.flush(); bw.close();
	}
}