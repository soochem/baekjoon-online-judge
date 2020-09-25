package dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

// 12101 1,2,3 ¥ı«œ±‚2

public class DFS_12101 {
	static StringBuilder sb;
	static int n, k, cnt;
	static Stack<Integer> stk;
	
	static void dfs (int x) {		
		if (x < 0) return;
		if (x == 0) {			
			if (++cnt == k) {
				sb.append(String.valueOf(stk.pop()));
				while (!stk.isEmpty()) {
					sb.insert(0, "+");
					sb.insert(0, String.valueOf(stk.pop()));
				}
			}
			return;
		}
		
		for (int i=1; i<=3; i++) {
			stk.add(i);
			dfs(x-i);
			if (stk.isEmpty()) return;
			stk.pop();
		}
		
		return;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		stk = new Stack<Integer>();
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		k = Integer.parseInt(s[1]);
		
		cnt = 0;
		dfs(n);
		
		if (sb.length() == 0) bw.write("-1");
		else bw.write(sb.toString());
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
