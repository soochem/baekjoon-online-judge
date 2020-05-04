package graph;

import java.io.*;
import java.util.*;

// 다익스트라
// 가중치가 작은 간선부터 탐색하는 그래프 탐색 방법

public class shortPass_1753_3 {
	public static int v, e, k, target;
	public static int[] dist;
	public static int[] visit;
	public static int[][] adj, weight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());  // 정점수
		e = Integer.parseInt(st.nextToken());  // 간선수
		k = Integer.parseInt(br.readLine());

		adj = new int[v+1][v+1];
		weight = new int[v+1][v+1];
		dist = new int [v+1];  // 최단 경로 가중치합
		visit = new int [v+1];  // 순환 방지
		
		Arrays.fill(dist, Integer.MAX_VALUE);  // 초기값 무한대로 초기화
		dist[k] = 0;
		visit[k] = 1;
		
		for (int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[a][b] = 1;
			
			int ori = weight[a][b];
			if (ori != 0 & ori > w || ori == 0)
			{
				weight[a][b] = w;
				if (a == k)
					dist[b] = w;
			}
  		}
		
		for (int i=0; i<v-1; i++) {
			int min_idx = 0;
			int min = Integer.MAX_VALUE;
			
			for (int d=1; d<v+1; d++) {
				if (dist[d] < min && visit[d] == 0) {
					min_idx = d;
					min = dist[d];
				}
			}
			
			for (int j=1; j<v+1; j++) {
				if (adj[min_idx][j] == 1) {
					if (dist[j] > dist[min_idx] + weight[min_idx][j]) {
						dist[j] = dist[min_idx] + weight[min_idx][j];
					}
				}
			}
			visit[min_idx] = 1;
		}
		
		for (int i=1; i<v+1; i++) {
			if (dist[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
		// count
//		4 5
//		1
//		1 3 2
//		3 2 4
//		1 4 2
//		4 2 1
//		1 3 5
	}
}
