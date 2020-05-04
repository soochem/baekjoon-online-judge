// 계란으로 계란 치기 DP로 접근? DFS
// N < 8, 2초라는 조건 때문에 DFS 가능
package samsung_sample;

import java.util.*;
import java.io.*;

public class BOJ_16987_eggegg_2 {
	public static int n, max;
	public static int [][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n+1][2];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken()); //내구도
			map[i][1] = Integer.parseInt(st.nextToken()); //무게
		}
		
		int[] pass = new int[n+1];
		for (int i=0; i<n; i++) pass[i] = map[i][0];
		
		max = -1;
		find(pass, 0, 0);
		System.out.println(max);
	}
	private static void find (int[] pass, int cur, int cnt) {
		//System.out.println();
		//System.out.println("cur: "+cur);
		//System.out.println("cnt : "+cnt);
		
		if (cur >= n) {  // n-1일 때도 계란을 치고 끝난다.
			if (max < cnt) max = cnt;
			System.out.println("cnt "+cnt+" max "+max);
			return;
		}
		
		if (pass[cur] <= 0) {
			find(pass, cur+1, cnt);
		}
		
		else {
			boolean end = true; // 멀쩡한 게 하나도 없을 때
			for (int i=0; i<n; i++) {
				if (i != cur) {
					if (pass[i] > 0) {
						end = false;
						pass[i] -= map[cur][1];
						pass[cur] -= map[i][1];
						//printp(pass);
						
						int tmp = cnt;
						if (pass[cur] <= 0) tmp++;
						if (pass[i] <= 0) tmp++;
						find(pass, cur+1, tmp);
						
						// array 값 복구!!
						pass[i] += map[cur][1];
						pass[cur] += map[i][1];
					}
				}
			}
			if (end) {
				if (max < cnt) max = cnt;
				return;
			}
		}
	}
//	private static void printp(int[] map) {
//		System.out.print("map : ");
//		for (int i=0; i<n; i++) {
//			System.out.print(map[i]+" ");
//		}
//		System.out.println();
//	}
}
