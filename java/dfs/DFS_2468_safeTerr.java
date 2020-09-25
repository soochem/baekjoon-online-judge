package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 2468 안전영역

public class DFS_2468_safeTerr {
	static int n, max;
	static int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};

	static boolean inside(int x, int y) {
		if (x >= 0 && y >= 0 && x < n && y < n) return true;
		return false;
	}
	
	static void dfs (int x, int y, int limit, int[][] map, int[][] visit) {
		int i, nx, ny;
		
		visit[x][y] = 1;
		
		for (i=0; i<4; i++) {
			nx = x + dir[i][0];
			ny = y + dir[i][1];
			
			if (!inside(nx, ny)) continue;
			if (map[nx][ny] <= limit || visit[nx][ny] != 0) continue;
			
			dfs(nx, ny, limit, map, visit);
		}
		return;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] s;
		int i, j, r, c, cnt, dwlim, uplim;
		int[][] map, visit;
		
		n = Integer.parseInt(br.readLine());
		max = 0;
		dwlim = 101; uplim = 0;
		map = new int[n][n];
		visit = new int[n][n];
		
		for (i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				uplim = (map[i][j] > uplim)? map[i][j] : uplim;
				dwlim = (map[i][j] < dwlim)? map[i][j] : dwlim;
			}
		}
		
		for (i=dwlim-1; i<=uplim; i++) {  // limit
			cnt = 0;
			for (int[] a:visit) Arrays.fill(a, 0);

			for (r=0; r<n; r++) {
				for (c=0; c<n; c++) {
					if (map[r][c] <= i || visit[r][c] == 1) continue;
//					System.out.println(r+ " " +c);
					cnt++;
					dfs(r, c, i, map, visit);
				}
			}
			
			if (cnt > max) max = cnt;
		}
		
		sb.append(max);
		System.out.println(sb.toString());
	}
}
