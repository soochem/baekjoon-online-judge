package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DP_2294_Coin2 {
	public static int N, K;
	public static final int MAX = 10001;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input;
		int i, j, tmp, tot, min;
		int[] map = new int[100];
		int[] dp = new int[10001];  // 경우의 수 저장
		
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		for (i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		for (i = 1; i <= K; i++) {
			min = MAX;
			for (j = 0; j < N; j++) {
				if (i-map[j] < 0) continue;
				min = (min > dp[i-map[j]])? dp[i-map[j]]: min;
			}
			dp[i] = (min == MAX)? MAX: min+1;
		}
		
		int ans = (dp[K] == MAX)? -1: dp[K];
		System.out.println(ans);
	}
}

// 1 9999
// -1
