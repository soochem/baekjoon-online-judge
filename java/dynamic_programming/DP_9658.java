// 돌게임 4 마지막으로 가져가는 사람이 진다.
// 선수 : SK
// d[i-1] = CK인 경우엔 SK가 1개 가져가면 CK는 다시 돌이 i-1개 인 SK 입장이라서 CK가 패배 (= SK 승리)
package dynamic_programming;

import java.io.*;

public class DP_9658 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		char[] memo = new char [n+1];
		
		if (n>=1) memo[1] = 'c'; // 막돌 : SK
		if (n>=2) memo[2] = 's'; // 막돌 : CY
		if (n>=3) memo[3] = 'c';
		if (n>=4) memo[4] = 's';
		for (int i=5; i<=n; i++) {
			if (memo[i-1] == 'c' || memo[i-3] == 'c' || memo[i-4] == 'c') memo[i] = 's';
			else memo[i] = 'c'; // 상근이가 이기려면 창영이로 끝나는 플레이(S)가 하나도 없어야함
		}
		
		if (memo[n] == 'c') System.out.println("CY");
		else System.out.println("SK");
				
	}
}
