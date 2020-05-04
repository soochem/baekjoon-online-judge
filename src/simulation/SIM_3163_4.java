// 떨어지는 개미 제출용
// 답은 int로 (integer X)
// arraycopy 2차원으로 하면 1차원 주소 참조라서 값 변경할때 같이 바뀜
package simulation;

import java.io.*;
import java.util.*;
   
public class SIM_3163_4 {	
	public static class Ant implements Comparable<Ant>{
		public Integer id, pos;
		public Ant(int id, int pos) {
			this.id = id;
			this.pos = pos;
		}
		@Override
		public int compareTo(Ant a) {
			int res = 0;
			res = this.pos - a.pos;
			if (this.pos == a.pos) {
				res = this.id - a.id;
			}
			return res;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		int n; // 개미수
		int l; // 막대 길이
		int k; // 목표 순서
		int[] id;
		
		for (int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			int end = n-1;
			int end_min = 0;
			
			Ant[] antArr = new Ant[n];
			id = new int[n+1];
			
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (b > 0) {
					antArr[i] = new Ant(b, l-a);
				}
				else {
					antArr[i] = new Ant(b, a);
					end_min++;  // 마이너스 개수
				}
				id[i] = b;
			}
			if (end_min >= 1) end_min--;
			
			for (int i=n-1; i>=0; i--) {
				if (id[i] > 0) {
					antArr[i].id = id[end];
					end--;
				}
				if (id[i] < 0) {
					antArr[i].id = id[end_min];
					end_min--;
				}
			}
			
			Arrays.sort(antArr);
			System.out.println(antArr[k-1].id.intValue());
		}
	}
}
