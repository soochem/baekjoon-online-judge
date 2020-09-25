package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class BFS_M2667 {
	public static int n;
	public static class Edge {
		public int x, y;
		public Edge (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[][] map = new int [n+1][n+1];  // 주어진 지도
		int[][] pass = new int [n+1][n+1];
		Queue<Edge> land = new LinkedList<Edge>(); // 탐색할 좌표
		
		for (int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=0; j<n; j++) {
				int m = (int) s.charAt(j)-48;
				map[i][j] = m; // i 행 j 열
				if (m == 1) {
					land.offer(new Edge(i, j));
				}
			}
		}
		
		//Edge cur = q.poll();
		int next_x, next_y;
		int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
		Queue<Edge> route = new LinkedList<Edge>(); // 탐색할 좌표
		
		int danzisu = 0;
		int danzip;
		Vector<Integer> danziVec = new Vector<Integer>();
		//int[] danzipArr = new int[26*26];
		
		while (!land.isEmpty()) {
			Edge cur = land.poll();
			if (pass[cur.x][cur.y] == 0) {
				route.offer(cur);
				pass[cur.x][cur.y] = 1;
				danzisu++;
				danzip = 1;
				
				while (!route.isEmpty()) {
					cur = route.poll();
					
					for (int i=0; i<4; i++) {
						next_x = cur.x + dir[i][0];
						next_y = cur.y + dir[i][1];
						
						if (inside(next_x, next_y)) {
							if (pass[next_x][next_y] == 0 && map[next_x][next_y] == 1) {
								//System.out.println(danzisu + " "+next_x +" "+next_y);
								route.offer(new Edge(next_x, next_y));
								pass[next_x][next_y] = 1;
								danzip++;
							}
						}
					}
				}
				danziVec.add(danzip);
			}
		}	
		// 출력부
		System.out.println(danzisu);
		Collections.sort(danziVec);
		for (int i=0; i<danzisu; i++) {
			System.out.println(danziVec.get(i));
		}
	}
	private static boolean inside(int a, int b) {
		if (a>=0 && b>=0 && a<=n && b<=n) return true;
		else return false;
	}
}
