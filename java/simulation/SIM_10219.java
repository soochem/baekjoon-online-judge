// i j ¸ÂÄ¡È¯

package simulation;

import java.io.*;
import java.util.*;

public class SIM_10219 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		int h, w;
		char[][] arr;
		
		for (int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			arr = new char [h][w];
			for (int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for (int j=0; j<w; j++) {
					arr[i][j] = s.charAt(j);
				}
			}
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					System.out.print(arr[i][w-j-1]);
				}
				System.out.println();
			}
		}
		
	}
}
