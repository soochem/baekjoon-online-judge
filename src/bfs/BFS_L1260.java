// DFS와 BFS

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class BFS_L1260 {
	public static int n,m,v;
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		v = Integer.parseInt(s[2]);
		
		// 인접 행렬 만들기 - 메모리
		int[][] adjArr = new int[n+1][n+1];
		for (int i=0; i<m; i++) {
			String[] s2 = br.readLine().split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			adjArr[a][b] = 1;
			adjArr[b][a] = 1;
		}
		// [a,b]
		
		int[] pass = new int[n+1];
		dfs(v, pass, adjArr);
		System.out.println();
		bfs(adjArr);
	}
	
	private static void dfs (int num, int[] pass, int[][] adjArr) {
		System.out.print(num+" ");
		pass[num] = 1;
		for (int i=1; i<n+1; i++) {
			if (adjArr[num][i] == 1 && pass[i] == 0) {
				dfs(i, pass, adjArr);
			}
		}
	}
	
	private static void bfs (int[][] adjArr) {
		int[] pass = new int[n+1];
		Queue<Integer> q = new LinkedList<Integer>(); // 참조타입만
		q.offer(v);
		int x = v;
		pass[x] = 1;
		
		while (!q.isEmpty()) {
			x = q.poll();
			System.out.print(x+" ");
			// 앞에서 poll하는 거랑 무슨 차이?
			for (int i=1; i<n+1; i++) {
				if (adjArr[x][i] == 1 && pass[i] == 0) {
					q.offer(i);
					pass[i] = 1;
				}
			}
		}
	}
}
