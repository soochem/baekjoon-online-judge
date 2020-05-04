package dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 1149 RGB거리
public class DP_1149 {
	static int n;
	static final int MAX = (int)1e7;
	static int[][] map, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		String[] s;

		n = Integer.parseInt(br.readLine());
		map = new int[n][3];
		dp = new int[n][3];
		
		// i번 집 j색으로 칠하는 비용
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if (i == 0) dp[i][j] = map[i][j];
			}
		}
		
		int min = MAX;
		for (int i=1; i<n; i++) {
			for (int j=0; j<3; j++) {
				min = MAX;
				for (int z=0; z<3; z++) {
					if (z == j) continue;
					if (min > dp[i-1][z]) min = dp[i-1][z];
				}
				dp[i][j] = min+map[i][j];
			}
		}
		
		min = Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
		bw.write(String.valueOf(min));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
