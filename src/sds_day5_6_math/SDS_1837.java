// 암호제작
// 큰 수 입력하기
// 큰 수의 나머지 구하기

package sds_day5_6_math;

import java.io.*;
import java.util.*;

public class SDS_1837 {
	private static int divBig (int div, String s) {
		int r = 0; //나머지
		for (int i = 0; i<s.length(); i++) {
			r *= 10;
			r += s.charAt(i) - '0';
			r %= div;
		}
		return r;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int k;
		String p;
		boolean[] visited;
		boolean flag;
		// 입력
		ArrayList<Integer> sosu = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		p = st.nextToken();
		k = Integer.parseInt(st.nextToken());
		visited = new boolean [k+1];

		// 짝수 처리
		sosu.add(2);
		for (int i=4; i<k; i+=2) {
			visited[i] = true;
		}
		// 홀수 처리
		for (int i=3; i<k; i+=2) {
			if (!visited[i]) {
				sosu.add(i);
//				System.out.println(i);
				for (int j=2; j*i<k; j++) {
					if (!visited[j*i])
						visited[j*i] = true;
				}
			}
		}
		
		int so, r;
		so = 0;
		flag = false;
		
		for (int i=0; i<sosu.size(); i++) {
			so = sosu.get(i);
			if (divBig(so, p) == 0) {
//				System.out.println(so);
				flag = true;
				break;
			}
		}
		
		if (!flag) {
			bw.write("GOOD");
		}
		else {
			bw.write("BAD ");
			bw.write(String.valueOf(so));
		}
		bw.write("\n");
		bw.flush(); bw.close();
	}
}
