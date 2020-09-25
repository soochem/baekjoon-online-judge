// 소수의 연속합
// 범위 주의
// *** 투포인터 조건, 로직 숙지
 
package sds_summer.day5_6_math;

import java.io.*;
import java.util.*;

public class SDS_1644 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int n, cnt;
		boolean[] visited;
		ArrayList<Integer> sosu = new ArrayList<>();
		
		// 입력
		cnt = 0;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		visited = new boolean [n+1];

		// 짝수 처리
		sosu.add(2);
		for (int i=4; i<=n; i+=2) {
			visited[i] = true;
		}
		// 홀수 처리
		for (int i=3; i<=n; i+=2) {
			if (!visited[i]) {
				sosu.add(i);
				for (int j=2; j*i<=n; j++) {  // 모두 n을 포함하여야한다.
					if (!visited[j*i])
						visited[j*i] = true;
				}
			}
		}
		
		int left, right; left = 0; right = 1; // 같게두면 망하는듯 (상관업슬듯)
		int sum = 0;
		sum = sosu.get(0);
	
		while (left <= right) {
			if (sum == n) cnt++;  // right가 1이기 때문에 처음에 체크해야함
			
			if (sum >= n) {
				sum -= sosu.get(left++);
			}
			else if (right == sosu.size()) break;
			else {
				sum += sosu.get(right++);  // 연산자 위치 매우 중요
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.write("\n");
		bw.flush(); bw.close();
	}
}
