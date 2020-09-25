// Union find 로 할 수 있다.
// level 3 트리로도 구현할 수 있다. - logn

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BFS_L11724 {
	public static int n, m;
	public static int[][] edge;
	public static int[] pass;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		edge = new int[n+1][n+1];
		pass = new int[n+1];
		
		for (int i=0; i<m; i++) {
			String[] s2 = br.readLine().split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			edge[a][b] = edge[b][a] = 1;
		}
		
		int num_set = 0;
		
		if(m >= 1) {
			for (int i=1; i<=n; i++) {
				if (pass[i] == 0) {
					num_set++;
					pass[i] = 1;
					dfs(i);
				}
			}
		}
		System.out.println(num_set);
	}
	
	private static void dfs (int v) {
		for (int i=1; i<=n; i++) {
			if (edge[v][i] != 0 && pass[i] == 0) {
				pass[i] = 1;
				dfs(i);
			}
		}
	}
}
