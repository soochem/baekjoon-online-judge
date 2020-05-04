package samsung_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 14500 테트로미노

public class Sam_14500_2_dfs {
	static int n, m, max;
	static int[][] map;
	static boolean[][] visit;
//	static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}};
	
	static boolean inside (int i, int j) {
		if (i >= 0 && i < n && j >= 0 && j < m) return true;
		else return false;
	}
	
	static void dfs (int i, int j, int depth, int ans) {
		int nexti, nextj;
		if (depth >= 4) {
			if (depth != 4) return;
			if (max < ans) max = ans;
//			for (boolean[] vv: visit) {
//				for (boolean v:vv) System.out.print(v+" ");
//				System.out.println();
//			}System.out.println();
			return;
		}
		
		if (!visit[i][j]) ans += map[i][j];
		
		for (int d=0; d<2; d++) {
			nexti = i + dir[d][0];
			nextj = j + dir[d][1];
			
			if (inside(nexti, nextj)) {
				if (visit[nexti][nextj]) continue;
				
				// 근처 한 개 지나가기
				visit[i][j] = true;
				dfs(nexti, nextj, depth + 1, ans);
				
				if (depth >= 3) continue;
				
				// 근처 두 개 지나가기
				visit[nexti][nextj] = true;
				dfs(i, j, depth + 1, ans + map[nexti][nextj]);
				visit[nexti][nextj] = false;
				visit[i][j] = false;
			}
		}
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
				dfs(i, j, 0, 0);
			}
		}
		
		sb.append(max);
		System.out.println(sb.toString());
	}
}
