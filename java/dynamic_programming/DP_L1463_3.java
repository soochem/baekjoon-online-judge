// 런타임에러 ? 배열 [N+1]로 하면 Index 에러 남
// 조건문 else if? if?
// 틀렸습니다? 조건문 조건 오타 주의

package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_L1463_3{
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(makeOne(n));
	}
	
	private static int makeOne (int x) {
		int[] memo = new int [1000001]; // 배열 길이 1000000보다 같거나 작은
		memo[1] = 0; // 1은 연산 안해도 1
		memo[2] = 1;
		memo[3] = 1;
		
		if (x>3) {
			for (int i=4; i<=x; i++) {
				int min = memo[i-1];
				if (i%3 == 0) {
					if (min > memo[i/3]) min = memo[i/3];
				}else if (i%2 == 0) {
					if (min > memo[i/2]) min = memo[i/2];
				}
				memo[i] = min+1;
				//Math.min(memo[i-1], Math.min(memo[i/3], memo[i/2]));
			}
			
		}
		return memo[x];
	}
}
