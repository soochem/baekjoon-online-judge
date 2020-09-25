package etc;

// 11401 이항 계수 3
import java.io.*;

public class BOJ_11401_pt2 {
	static int n, k;
	static final int max = (int)1e9+7;
	static int[][] dp;  // n*k = 400만*400만 byte ~ 64조 바이트
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		k = Integer.parseInt(s[1]);

		dp = new int[n+1][k+1];
		
		for (int i=0; i<=n; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
		}
		
//		for (int i=2; i<=n; i++) {
//			for (int j=1; j<=k; j++) {
//				dp[i][j] = (dp[i-1][j]+dp[i-1][j-1]) % max;
//			}
//		}
//		int now, bef1, bef2;
//		bef1 = 1; bef2 = 1;
//		for (int i=2; i<=n; i++) {
//			for (int j=1; j<=k; j++) {
//				now = (bef1+bef2) % max;
//			}
//		}
//		System.out.println(dp[n][k]);
		
	}
}