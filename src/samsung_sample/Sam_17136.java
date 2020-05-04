package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// 17136 색종이 붙이기

public class Sam_17136 {
	static final int max = 5;  // 최대 길이
	static int[][] map, dp;
	
	static void find(int x, int y) {
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s;
		String[] str;
		
		map = new int[10][10];
		dp = new int[10][10];
		
		
		for (int j=0; j<10; j++) {
			str = br.readLine().split(" ");
			for (int i=0; i<10; i++) {
				map[j][i] = Integer.parseInt(str[i]);
			}
		}
		
		// find
		int max = -1;
//		for (int i=0; i<10; i++) {
//			dp[0][i] = map[0][i];
//			dp[i][0] = map[i][0];
//		}
		for (int i=0; i<10; i++) System.arraycopy(map[i], 0, dp[i], 0, 10);;
		for (int j=0; j<10; j++) {
			for (int i=0; i<10; i++) {
				System.out.print(dp[j][i]+" ");
			}
			System.out.println();
		}
		int tmp = 0;
		int[] cnt = new int[6];
		
		for (int j=1; j<10; j++) {
			for (int i=1; i<10; i++) {
				if (map[j][i] == 0) continue;
				tmp = Math.min(Math.min(dp[j-1][i], dp[j][i-1]), dp[j-1][i-1]);
				if (tmp != 0) {
					if (tmp >= 5) {
						cnt[tmp]++;
						tmp = 0; // 크기 넘길 때
					}
					dp[j][i] = tmp+1;
				}
//				else { // 둘 중 한쪽이라도 정사각형 벗어난 조건
//					dp[j][i] = 0;
//					cnt[tmp]++;
//				}
			}
		}
		
		for (int j=0; j<10; j++) {
			for (int i=0; i<10; i++) {
				System.out.print(dp[j][i]+" ");
			}
			System.out.println();
		}
	}
}
