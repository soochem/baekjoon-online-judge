package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 동전1

public class DP_2293_Coin1_2 {
	static int N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input;
		int i, j;
		int[] map = new int[101];
		int[] dp = new int[10001];  // 경우의 수 저장
		
		// 입력
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		for (i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		// DP
		dp[0] = 1;
		for (i = 0; i < N; i++) {  // 아래서 부터 경우의 수 채우기
			for (j = 1; j <= K; j++) { // 앞의 경우에다가 새로운 코인쓰기
				if (j < map[i]) continue;
				dp[j] += dp[j-map[i]];
				System.out.println(dp[j]+" "+j);
			}
		}
		
		System.out.println(dp[K]);
	}
}
