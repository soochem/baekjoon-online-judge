package etc;

import java.io.*;
import java.util.*;

public class L1051_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n+1][m+1];
		for (int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=0; j<m; j++) {
				map[i][j] = (int)s.charAt(j)-48;
			}
		}
		
		int max = 0;
		int len;
		if (n < m) len = n-1; // 최소 길이
		else len = m-1;
		boolean flag = true;
		int i = 0;
		
		while (i < n && flag) {
			for (int j=0; j<m; j++) {
				if (n-i-1 <= max || max >= len) {
					flag = false;
					break;
				}
				for (int l=max; l<=len; l++) {
					if (l>=1 && i+l<n && j+l<m) {
						if (isRect(i, j , l, map)) {
							if (l > max) {
								max = l;
							}
						}
					}
				}
			}
			i++;
		}
		
		if (n==1 || m==1) System.out.println(1);
		else System.out.println((int) Math.pow(++max, 2));
	}
	private static boolean isRect(int a, int b, int len, int[][] map) {
		int ans = map[a][b];
		if (ans == map[a+len][b+len] && ans == map[a][b+len] && ans == map[a+len][b]) return true;
		else return false;
	}
}
