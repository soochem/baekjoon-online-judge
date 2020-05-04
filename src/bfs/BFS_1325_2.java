// 효율적인 해킹

// depth가 아니라 해킹 가능한 컴퓨터 수를 구해야 한다.
// 인접리스트 무조건 만들어야 함 (n이 큼)
// 위상정렬 - DAG 여야 한다.
package bfs;

import java.util.*;
import java.io.*;

public class BFS_1325_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> arr = new ArrayList<>(); // 인접 리스트
		for (int i=0; i<n+1; i++) {
			arr.add(new ArrayList<Integer>());
		}
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr.get(x).add(y);
		}
		
		int[] pass = new int [n+1];
		int[] visit = new int [n+1];
		int max = -1;
		
		Queue<Integer> q = new LinkedList<Integer>();
		for (int j=1; j<=n; j++) {
			q.offer(j);
			visit = new int [n+1];
			visit[j] = 1;
			
			while (!q.isEmpty()) {
				int cur = q.poll();
				
				for (int item : arr.get(cur)) {
					if (visit[item] == 0) {
						q.offer(item);
						visit[item] = 1;
						pass[item] += visit[item];
						
						if (pass[item] > max) max = pass[item];
					}
				}
			}
		}
		for (int i=1; i<=n; i++) {
			//System.out.println(pass[i]+" ");
			if (pass[i] == max) System.out.print(i+" ");
		}	
	}
}
