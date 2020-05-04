package etc;

import java.io.*;
import java.util.*;

public class L1051 {

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
					//System.out.println(i+" "+(n-i-1)+" max "+max+ " len "+len);
					flag = false;
					break;
				}
				for (int l=max; l<=len; l++) {
					//System.out.println(i+" "+j+" "+l);
					if (l>=1 && i+l<n && j+l<m) {
						if (map[i][j] == map[i+l][j+l] && map[i][j] == map[i][j+l] && map[i][j] == map[i+l][j]) {
							if (l > max) max = l;
							//System.out.println(max);
						}
					}
				}
			}
			i++;
		}
		
		if (n==1 || m==1) System.out.println(1);
		else {
			++max;
			System.out.println(max*max);
		}
	}
}
