package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// 17070 파이프 옮기기1
// 완탐 시간초과 ㅂㄷㅂㄷ
// 백트레킹

public class Sam_17070_dp {
	static int n;
	static int[][] map;
	static int[][][] dp;
	
	static boolean inside(int x, int y) {
		if (x >= 0 && y >= 0 && x < n && y < n) return true;
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		dp = new int[n+1][n+1][3];
		String[] s;
		
		for (int[] tmp : map) Arrays.fill(tmp, 1);
		for (int i=1; i<=n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<n; j++) {
				map[i][j+1] = Integer.parseInt(s[j]);
			}
		}
		
		// dp
		dp[1][2][2] = 1;
		for (int j=3; j<=n; j++) {
			if (map[1][j] == 1) continue;  // 예외처리
			dp[1][j][2] = dp[1][j-1][1]+dp[1][j-1][2];
		}
		for (int i=2; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (map[i][j] == 1) continue;
				dp[i][j][0] = dp[i-1][j][0]+dp[i-1][j][1];
				if (map[i-1][j] == 0 && map[i][j-1] == 0)
					dp[i][j][1] = dp[i-1][j-1][0]+dp[i-1][j-1][1]+dp[i-1][j-1][2];
				dp[i][j][2] = dp[i][j-1][1]+dp[i][j-1][2];
			}
		}
		
		int ans = dp[n][n][0]+dp[n][n][1]+dp[n][n][2];
		bw.write(String.valueOf(ans));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
