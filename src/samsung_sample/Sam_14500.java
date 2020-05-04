package samsung_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sam_14500 {
	// 14500 테트로미노
	static int n, m, max;
	static int[][] map;
	static boolean[][] visit;
	static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	
	static boolean inside (int i, int j) {
		if (i >= 0 && i < n && j >= 0 && j < m) return true;
		else return false;
	}
	
	static void dfs (int i, int j, int depth, int ans) {
		int nexti, nextj;
		if (depth >= 4) {
			if (max < ans) max = ans;
			return;
		}
		visit[i][j] = true;
		ans += map[i][j];
		
		for (int d=0; d<4; d++) {
			nexti = i + dir[d][0];
			nextj = j + dir[d][1];
			if (inside(nexti, nextj)) {
				if (visit[nexti][nextj]) continue;
				dfs(nexti, nextj, depth + 1, ans);
				
				if (depth == 3) continue;
				visit[nexti][nextj] = true;
				dfs(i, j, depth + 2, ans + map[nexti][nextj]);
				visit[nexti][nextj] = false;
			}
		}
		visit[i][j] = false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		
		max = 0;
		map = new int[n][m];
		visit = new boolean[n][m];
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
//				visit[i][j] = true;
				dfs(i, j, 0, 0);
//				visit[i][j] = false;
			}
		}
		
		sb.append(max);
		System.out.println(sb.toString());
	}
}
