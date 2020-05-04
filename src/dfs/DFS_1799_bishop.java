package dfs;
import java.io.*;
import java.util.*;

// 백트레킹

public class DFS_1799_bishop {
	public static int n, max;
	public static int[][] map;
	public static int[][] visited;  // 합 ,열? 행,열?
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int [n+1][n+1];
		visited = new int [n+1][n+1];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);
		int res = max;
		max = -1;
		dfs(1, 0);
		res += max;
		
		System.out.println(res);
	}
	
	private static void dfs (int s, int cnt) {
		if (max < cnt) max = cnt;  // s가 뒤에서 +2되기 때문에 max 갱신은 여기서 해야한다
		
		if (s > 2*(n-1)) return;
		
		System.out.println(s);
		if (s < n) {
			for (int i=0; i<=s; i++) {
				// i = 0~s
				//System.out.println(i+" "+(s-i)+" ");
				if (poss(i, s-i) && map[i][s-i] == 1) {
					visited[i][s-i] = 1;
					System.out.println("suc "+i+" "+(s-i)+" ");
					dfs(s+2, cnt+1);
					visited[i][s-i] = 0;
				}
			}
		}
		else {
			for (int i=s-n+1; i<n; i++) {
				// i = s-n-1 ~ n-1
				//System.out.println(i+" "+(s-i)+" ");
				if (poss(i, s-i) && map[i][s-i] == 1) {
					visited[i][s-i] = 1;
					System.out.println("suc "+i+" "+(s-i)+" ");
					dfs(s+2, cnt+1);
					visited[i][s-i] = 0;
				}
			}
		}
		dfs(s+2, cnt);  // 아무것도 안해도 진행해야 한다.
		print(visited);
		return;
	}
	
	private static boolean poss (int x, int y) {
		boolean flag = true;
		while (inside(x, y)) {
			if (visited[x][y] == 1) flag = false;
			x--; y--;
		}
		return flag;
	}
	
	private static boolean inside (int x, int y) {
		if (x>=0 && y>=0 && x<n && y<n) return true;
		else return false;
	}
	
	private static void print (int[][] map) {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
