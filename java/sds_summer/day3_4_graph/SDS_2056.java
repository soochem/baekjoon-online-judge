// 위상정렬로 풀거

package sds_summer.day3_4_graph;

import java.io.*;
import java.util.*;

public class SDS_2056 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int n, m, a, b, prev; a=b=0;
		int[] indeg;
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		int[] d, c;
		
		n = Integer.parseInt(br.readLine());
		
		indeg = new int[n+1];
		d = new int[n+1];
		c = new int[n+1];
		//ans = new ArrayList<Integer>();
		for (int i=0; i<n+1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			while (true) {
				prev = Integer.parseInt(st.nextToken());
				if (prev == -1) break;
				indeg[i]++;
				adjList.get(prev).add(i);
			}
			c[i] = a;
		}
		
		for (int i=1; i<n+1; i++) {
			if (indeg[i] == 0) {
				q.add(i);
				d[i] = c[i];
//				System.out.println(c[i]);
//				System.out.println(i);
			}
		}
		
		while (!q.isEmpty()) {
			a = q.poll();
			for (int ne:adjList.get(a)) {
				if (d[ne] < d[a] + c[ne]) d[ne] = d[a] + c[ne];  // 더 적은 걸로 선택
				if (--indeg[ne] == 0) {
					q.add(ne);
				}
			}
		}
		
		for (int i=1; i<=n; i++) bw.write(String.valueOf(d[i])+"\n");
		
		bw.flush(); bw.close();
	}
}

