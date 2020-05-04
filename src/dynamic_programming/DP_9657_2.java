// 돌게임 3 마지막으로 가져가는 사람이 이긴다.
// 선수 : SK
package dynamic_programming;

import java.io.*;

public class DP_9657_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		boolean[] memo = new boolean [n+1];
		if (n>=1) memo[1] = true;
		if (n>=2) memo[2] = false;
		if (n>=3) memo[3] = true;
		if (n>=4) memo[4] = true;
		for (int i=5; i<=n; i++) {
			memo[i] = memo[i-1] && memo[i-3] && memo[i-4] ? false : true;
			// 몇개를 내든 무조건 상근이만 이기는 완벽한 경우 => 창영이가 이기는 경우
		}
		if (memo[n]) System.out.println("SK");
		else System.out.println("CY");
				
	}
}
