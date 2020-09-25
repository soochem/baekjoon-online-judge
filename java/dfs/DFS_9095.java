package dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 9095 1,2,3 ¥ı«œ±‚

public class DFS_9095 {
	static int n, k, cnt;
	
	static int dfs (int x) {
		if (x < 0) return 0;
		if (x == 0) return 1;
		
		int c = 0;
		for (int i=1; i<=3; i++) {
			c += dfs(x - i);
//			cnt += dfs(x-i);
		}
		return c;
//		return 0;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		cnt = 0;
		n = Integer.parseInt(br.readLine());
		cnt += dfs(n);
		
		bw.write(String.valueOf(cnt));
		bw.write("\n");
		
		bw.flush();
		bw.close();
	}
}
