package samsung_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sam_14501 {
	static int n, max;
	static boolean[] visited;
	static int[][] map;
	
	static void dfs(int depth) {
		if (depth > n) {
			go();
//			for (boolean v : visited) System.out.print(v+" ");
//			System.out.println();
			return;
		}
		
		if (depth+map[depth][1] <= n+1) {
			visited[depth] = true;
			dfs(depth+map[depth][1]);
			visited[depth] = false;
		}
		dfs(depth+1);
	}
	
	static void go() {
		int ans = 0;
		
		for (int i=1; i<=n; i++) {
			if (visited[i]) ans += map[i][2];
		}
		
		if (ans > max) max = ans;
		return;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());

		map = new int[n+1][3];  // 시작, 걸리는 시간, 수익
		visited = new boolean[n+1];
		
		for (int i=1; i<=n; i++) {
			String[] s = br.readLine().split(" ");
			map[i][0] = i;
			map[i][1] = Integer.parseInt(s[0]);
			map[i][2] = Integer.parseInt(s[1]);
		}
		
		max = 0;
		dfs(1);
		sb.append(max);
		System.out.println(sb.toString());
	}
}
