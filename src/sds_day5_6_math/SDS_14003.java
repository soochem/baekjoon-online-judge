//5
//1 4 3 4 6

//6
//-10 20 20 -30 30 50

//5
//-10 20 -10 30 -10

package sds_day5_6_math;

import java.util.*;
import java.io.*;

public class SDS_14003 {
	static int n, cnt, max;
	static int[] map, p, second;

	static int search(int item) {
		// max와 일치/작은 원소의 idx 반환
		// p에서 찾아서 넣는다 idx에 넣는다
		// 같은 애는 그 전이나 다음거 반환하면 안됨
		int left, right, mid;
		left = 1;  // idx : 1~cnt (0으로 하거나)
		right = cnt;
		
		// lower bound // right는 타이트(포함) left는 넓게
		while (left < right) {
			mid = (left + right)/2;
			
			if (p[mid] > item) {
				right = mid;
			}
			else if (p[mid] == item) {
				return mid;
			}
			else left = mid+1;
		}
		
//		System.out.println(item);
//		System.out.println("bis "+right);
//
//		for (int i = 1; i <= cnt; i++)	System.out.print(p[i]+" ");
//		System.out.println();
		
		return right;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n;
		
		n = Integer.parseInt(br.readLine());

		map = new int[n+1];
		p = new int[n+1];
		second = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		max = -1000000001;
		cnt = 0;
		for (int i=0; i<n; i++) {
			if (max >= map[i]) {
				// max 보다 작은 원소 발견하면 이분 탐색으로 자리 찾아 넣음
				int idx = search(map[i]);  // 실제 인덱스는 1 작다
				p[idx] = map[i];
				second[i] = idx; // 실제 위치가 될 수 있는 정보
			}
			else {  // 새로운 max찾으면 끝에 추가
				p[++cnt] = map[i];
				second[i] = cnt;
			}
			max = p[cnt];  // 마지막 원소가 바뀔 수 있다.
//			System.out.println("max "+max);
		}
		
//		System.out.print("second: ");
//		for (int i = 0; i <= n; i++) System.out.print(second[i]+" ");
//		System.out.println();
		
		String ans = "";
		int tmp = cnt;
		for (int i=n-1; i>=0; i--) {
//			System.out.println(tmp+" "+i);
			if (tmp == 0) break;
			if (second[i] == tmp) {
				ans = String.valueOf(map[i]+" ") + ans;
				tmp--;
			}
		}
		
		bw.write(String.valueOf(cnt)+"\n");
//		for (int i = 1; i <= n; i++) bw.write(String.valueOf(p[i])+" ");
		bw.write(ans);
		bw.write("\n");
		bw.flush(); bw.close();
	}
}
