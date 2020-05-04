//삼성sw역량기출
//2048 (Easy)
package samsung_sample;

import java.io.*;
import java.util.*;

public class Sam_2048_12100 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int [n+1][n+1];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
		}
		
		
	}
}
