package sds_day3_4_graph;
//15480 LCA와 쿼리
//7
//1 2
//2 3
//3 4
//4 5
//5 6
//6 7
//3
//1 2 7
//2 1 6
//4 1 7

import java.io.*;
import java.util.*;

public class SDS_15480_LCAandquery_2 {
	static int n, m, max_d;
	static int[] depth;
	static int[][] dp;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> adjArr;
	
	static class NewComp implements Comparator<int[]> {
		public int compare(int[] a, int[] b) {
			return a[1] < b[1] ? 1:-1;
		}
	}
	
	private static void dfs(int cur, int cnt) {
		depth[cur] = cnt;
		visited[cur] = true;
		
		// dp 채우기
		for (int i=1; i<=max_d; i++) {
			dp[cur][i] = dp[dp[cur][i-1]][i-1];
		}
		for (int next: adjArr.get(cur)){
			if (!visited[next]) {
				dp[next][0] = cur;
				dfs(next, cnt+1);
			}
		}
		
		return;
	}
	
	private static double log2(int n) {
		return Math.log10(n)/Math.log10(2);
	}
	
	private static int lca(int u, int v) {
		int i = 0;
		
		if (depth[u] < depth[v]) {  // u의 depth가 더 깊음
			int tmp = v;
			v = u;
			u = tmp;
		}
		for (i=max_d; i>=0; i--) {
			if (dp[u][i] != 0 && depth[v] <= depth[dp[u][i]])
				u = dp[u][i]; // u를 계속 u의 2^i번째 조상으로 업데이트
		}
//		System.out.println("uv "+u+" "+v);
		if (u == v) {
			return u;
		}
		for (i=max_d; i>=0; i--) {  // 같은 높이인데 조상이 다름? -> 둘이 같이 올림
			if (dp[u][i] != dp[v][i]) {
				u = dp[u][i]; v = dp[v][i];
			}
		}
		return dp[u][0];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int r, u, v; r=u=v=0;
		
		adjArr = new ArrayList<>();
		n = Integer.parseInt(br.readLine());
		for (int i=0; i<=n+1; i++) {
			adjArr.add(new ArrayList<Integer>());
		}

		max_d = (int) Math.floor(log2(n));  // 0부터~2^k라서 -1하면 안됨~
		depth = new int[n+1];
		dp = new int[n+1][max_d+1];  // i번 노드의 2^j번째 조상
		// 메모리 초과 ***
		
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adjArr.get(u).add(v);
			adjArr.get(v).add(u);
		}
		
		// dfs - 트리 연결하기, 루트는 1
		visited = new boolean[n+1];
		dfs(1, 0);
		
		// lca - 두 정점의 공통 조상?
		m = Integer.parseInt(br.readLine());
		int[][] ans = new int[3][2];
		NewComp newSort = new NewComp();
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			
			ans[0][0] = lca(u, v);
			ans[1][0] = lca(u, r);
			ans[2][0] = lca(v, r);
			
			for (int j=0; j<3; j++) ans[j][1] = depth[ans[j][0]];
			Arrays.sort(ans, newSort); // 깊이가 깊은 게 답
			
			bw.write(String.valueOf(ans[0][0]));
			bw.write("\n");
		}
		
		bw.flush(); bw.close();
	}
}
