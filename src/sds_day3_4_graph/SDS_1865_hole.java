// 도로는 양방향
// 어차피 마지막 업뎃은 i==n-1일 때 (n번째 간선 사용)
//3 4 1
//1 2 2
//3 2 4
//3 1 8

package sds_day3_4_graph;

import java.util.*;
import java.io.*;

public class SDS_1865_hole {
	static int n, m, w;
	static int MAX = (int) 1e9;
	static int[] d;
	
	static class Edge {
		int from, dest, weight;
		Edge(int d, int w){
			this.dest = d;
			this.weight = w;
		}
		Edge(int f, int d, int w){
			this.from = f;
			this.dest = d;
			this.weight = w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s;
		int a, b, c;
		boolean negFlag, update;
		
		int testcase = Integer.parseInt(br.readLine());
		
		while (--testcase >= 0) {
			s = br.readLine().split(" ");
			n = Integer.parseInt(s[0]); //정점
			m = Integer.parseInt(s[1]); //간선
			w = Integer.parseInt(s[2]); //음수 가중치 정점수
			
//			ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
//			for (int i=0; i<=n; i++) adj.add(new ArrayList<>());
			ArrayList<Edge> edges = new ArrayList<>();
			d = new int[n+1];
			Arrays.fill(d, MAX);
			
			for (int i=0; i<m+w; i++) {
				s = br.readLine().split(" ");
				a = Integer.parseInt(s[0]);
				b = Integer.parseInt(s[1]);
				c = Integer.parseInt(s[2]);	
				if (i >= m) c *= -1;
				edges.add(new Edge (a, b, c));
				if (i < m) edges.add(new Edge (b, a, c));
			}
			
			negFlag = false;
			update = false;
			d[1] = 0;
			
			for (int i=0; i<n; i++) {
				update = false;
				for (int j=0; j<2*m+w; j++) {
					a = edges.get(j).from;
					b = edges.get(j).dest;
					c = edges.get(j).weight;
					
					if (d[a] != MAX && d[b] > d[a]+c) {  // from이 MAX? dest가 MAX?
//						if (i == n-1) negFlag = true;
						d[b] = d[a]+c;
						update = true;
					}
//					for (int dd:d) System.out.print(dd+" ");
//					System.out.println();
				}
//				System.out.println();
				if (!update) break;  // 노 업뎃
			}
			
			if (update) bw.write("YES");
			else bw.write("NO");
			bw.write("\n");	
		}

		bw.flush();
		bw.close();
	}
}
