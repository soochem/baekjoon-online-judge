package dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// 1012 ¿Ø±‚≥Û	πË√ﬂ
public class DFS_1012 {
	static int n, m, k;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};
	
	static int search() {
		int cnt = 0;
		for (boolean[] v:visited) Arrays.fill(v, false);
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					cnt++;
					visited[i][j] = true;
					dfs(i, j);
				}
			}
		}
		return cnt;
	}
	
	static boolean inside(int x, int y) {
		if (x>=0 && x<m && y>=0 && y<n) return true;
		else return false;
	}
	
	static void dfs(int x, int y) {
		int nxtx, nxty;
		visited[x][y] = true;
		
		for (int d=0; d<4; d++) {
			nxtx = x+dir[d][0];
			nxty = y+dir[d][1];
			
			if (inside(nxtx, nxty)) {
				if (!visited[nxtx][nxty] && map[nxtx][nxty] == 1) {
					dfs(nxtx, nxty);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s;
		int a, b;
		
		map = new int[50][50];
		visited = new boolean[50][50];
		
		int test = Integer.parseInt(br.readLine());
		
		while (--test >= 0) {
			for (int[] tmp:map) Arrays.fill(tmp, 0);

			s = br.readLine().split(" ");
			m = Integer.parseInt(s[0]);
			n = Integer.parseInt(s[1]);
			k = Integer.parseInt(s[2]);
			
			for (int i=0; i<k; i++) {
				s = br.readLine().split(" ");
				a = Integer.parseInt(s[0]);
				b = Integer.parseInt(s[1]);
				map[a][b] = 1;
			}
			
			bw.write(String.valueOf(search()));
			bw.write("\n");
		}

		bw.flush();
		bw.close();
	}
}
