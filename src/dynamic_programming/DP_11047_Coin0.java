package dynamic_programming;

// 동전 0

import java.io.*;

public class DP_11047_Coin0 {
	static int n, tar;
	static int[] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] s;
		
		s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		tar = Integer.parseInt(s[1]);
		
		int ans = 0;
		map = new int[n];
		
		for (int i = 0; i < n; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = n-1; i >= 0; i--) {
			// 오름차순
			if (map[i] > tar) continue;
			ans += tar/map[i];
			tar %= map[i];
		}
		
		bw.write(String.valueOf(ans));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
