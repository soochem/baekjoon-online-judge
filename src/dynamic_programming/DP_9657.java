// 돌게임 3 마지막으로 가져가는 사람이 이긴다.
// 선수 : SK
package dynamic_programming;

import java.io.*;

public class DP_9657 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		char[] memo = new char [n+1];
		
		if (n>=1) memo[1] = 's';  // 막돌 : SK
		if (n>=2) memo[2] = 'c';  // 막돌 : CY
		if (n>=3) memo[3] = 's';
		if (n>=4) memo[4] = 's';
		for (int i=5; i<=n; i++) {
			if (memo[i-1] == 's' && memo[i-3] == 's' && memo[i-4] == 's') memo[i] = 'c';
			else memo[i] = 's'; // 상근이가 이기려면 창영이로 끝나는 플레이가 하나라도 있어야 함.
		}
		
		if (memo[n] == 'c') System.out.println("CY");
		else System.out.println("SK");
				
	}
}
