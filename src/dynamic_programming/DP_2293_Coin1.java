package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 동전1

public class DP_2293_Coin1 {
	static int N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input;
		int i, j, tmp, tot;
		int[] map = new int[100];
		int[] dp = new int[100];  // 경우의 수 저장
		
		// 입력
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		tot = 0;
		
		for (i = 0; i < N; i++) {
			tmp = Integer.parseInt(br.readLine());
			map[i] = tmp;
			dp[tmp] = 1;
			tot += tmp;
		}
		
		// dp
		// 갖고 있는 숫자들에 map의 아이템 더하기
		for (i = 0; i < N; i++) {
			for (j = tot; j >= 0; j--) {  // 거꾸로 내려오면서 더하기
				if (dp[j] == 0) continue;
				dp[dp[j]+map[i]] += 1;
			}
		}
		
		System.out.println(dp[K]);
	}
}
