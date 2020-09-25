package dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class DP_2342 {
	static int n;
	static int MAX = (int)1e6;
	static int[] map;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		map = new int[100001];
//		int[][] cost = new int[5][5];
//		for (int i=0; i<5; i++) {
//			for (int j=0; j<5; j++) {
//				if (j == 0) cost[i][j] = MAX;
//				else if (i == 0) cost[i][j] = 2;
//				else if (i == j) cost[i][j] = 1;
//				else {
//					if (j == i+1) cost[i][j] = 3;
//					else if (j == 1 && (i+1) > 4) cost[i][j] = 3;
//					else if (j == i-1)  cost[i][j] = 3;
//					else if (j == 4 && (i-1) == 0) cost[i][j] = 3;			
//					else cost[i][j] = 4;
//				}
//			}
//		}
//		for (int[]c:cost) {
//			for (int cc:c) System.out.print(cc+" ");
//			System.out.println();
//		}
		
		int[][] cost = {{MAX, 2, 2, 2, 2},
						{MAX, 1, 3, 4, 3},
						{MAX, 3, 1, 3, 4},
						{MAX, 4, 3, 1, 3},
						{MAX, 3, 4, 3, 1}};
		int tmp = 1;
		int i = 0;
		String[] s = br.readLine().split(" ");
		
		while (tmp != 0) {
			tmp = Integer.parseInt(s[i]);
			map[i++] = tmp;
		}
		
		n = i-1;		
		dp = new int[5][5][n+1];

		for (int[][] d:dp) {
			for (int[] dd:d) Arrays.fill(dd, MAX);
		}
		dp[map[0]][0][0] = 2; dp[0][map[0]][0] = 2;
		
		int target = 0;
		int min = MAX;
		
		for (i = 0; i < n-1; i++) { // 0~n-1
			target = map[i+1];
			
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 5; y++) {  // y = x+1 ¾ÈµÊ
					if (x == y || dp[x][y][i] == MAX) continue;
					dp[x][target][i+1] = Math.min(dp[x][target][i+1], dp[x][y][i]+cost[y][target]);	// y¹Ù²ðµ¿¾È ´Þ¶óÁü
					dp[target][y][i+1] = Math.min(dp[target][y][i+1], dp[x][y][i]+cost[x][target]); // ¿ÖÁê? ¶È°°ÀÌ x¹Ù²ðµ¿¾È ´Þ¶óÁü

					if (i == n-2) {
						min = Math.min(Math.min(min, dp[x][target][i+1]), dp[target][y][i+1]);
					}
				}
			}
		}
		
		bw.write(String.valueOf(min));
		bw.write("\n");
		bw.flush();
	}
}
