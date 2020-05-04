package sds_day3_4_graph;

import java.io.*;
import java.util.*;

public class SDS_11438_lca2 {
	static final int MAX = 20;  // 최대 깊이 대략 노드 1E6까지
	static int n, m, max_d;
	static int[] par, depth;
	static int[][] dp;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> adjArr;
	
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
			if (depth[v] <= depth[dp[u][i]])
				u = dp[u][i]; // u를 계속 u의 2^i번째 조상으로 업데이트
		}
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
		
		int u, v; u=v=0;
		
		adjArr = new ArrayList<>();
		n = Integer.parseInt(br.readLine());
		for (int i=0; i<=n+1; i++) {
			adjArr.add(new ArrayList<Integer>());
		}

		max_d = (int) Math.floor(log2(n))-1;
		depth = new int[n+1];
		dp = new int[n+1][MAX];  // i번 노드의 2^j번째 조상
		
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

		for (int i=0; i<=n; i++) {
			for (int j=0; j<=n; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		
		// lca - 두 정점의 공통 조상?
		m = Integer.parseInt(br.readLine());
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(lca(u, v)));
			bw.write("\n");
		}
		
		bw.flush(); bw.close();
	}
}
