package bfs;

import java.io.*;
import java.util.*;

public class BFS_14502 {
	static int n, m, safeArea, tmpArea, max;
	static int[][] map;
	static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	static boolean[][] visited;
	
	static class Pt {
		int x, y;
		Pt (int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean inside(int x, int y) {
		if (n > x && x >= 0 && m > y && y >= 0) return true;
		return false;
	}
	static void dfs (ArrayList<Pt> start, int curx, int cury) {		
		visited[curx][cury] = true;
		
		for (int d=0; d<4; d++) {
			int nextx = curx + dir[d][0];
			int nexty = cury + dir[d][1];

			if (inside(nextx, nexty)) {
				if (map[nextx][nexty] == 0 
					&& !visited[nextx][nexty]) {
					if (--tmpArea <= max) {  //볼 것도 없음
						return;
					}
					dfs(start, nextx, nexty);
				}
			}
		}
		return;
	}
	
//	static int bfs (ArrayList<Pt> start) {
//		Queue<Pt> q = new LinkedList<Pt>();
//		boolean[][] visited = new boolean[n][m];
//		
//		for (boolean[] v: visited) Arrays.fill(v, false);
//			
//		for (Pt p : start) {
//			q.add(p);
//			visited[p.x][p.y] = true;
//		}
//		int tmpArea = safeArea-3;
//		
//		while (!q.isEmpty()) {
//			Pt cur = q.poll();
//			int curx = cur.x;
//			int cury = cur.y;
////				System.out.println(curx+" "+cury);
//			
//			for (int d=0; d<4; d++) {
//				int nextx = curx + dir[d][0];
//				int nexty = cury + dir[d][1];
//				
//				if (inside(nextx, nexty)) {
//					if (map[nextx][nexty] == 0 
//						&& !visited[nextx][nexty]) {
//						if (--tmpArea <= max) {  //볼 것도 없음
//							return tmpArea;
//						}
//						visited[nextx][nexty] = true;
//						q.add(new Pt(nextx, nexty));
//					}
//				}
//			}						
//		}
//		return tmpArea;
//	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] s;
		
		ArrayList<Pt> start = new ArrayList<Pt>();
		max = 0;  // 초기값
		
		s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		
		map = new int[n][m];
		visited = new boolean[n][m];
		Pt[] safe = new Pt[n*m];
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<m; j++) {
				int tmp = Integer.parseInt(s[j]);
				map[i][j] = tmp;
				Pt p = new Pt(i, j);
				if (tmp == 0) {
					safe[safeArea] = p;
					safeArea++;
				}
				else if (tmp == 2) {
					start.add(p);
				}
			}
		}
		
		for (int i=0; i<safeArea; i++) {
			int x1 = safe[i].x;
			int y1 = safe[i].y;
			map[x1][y1] = 1;

			for (int j=i+1; j<safeArea; j++) {
				int x2 = safe[j].x;
				int y2 = safe[j].y;
				map[x2][y2] = 1;
				
				for (int k=j+1; k<safeArea; k++) {
					int x3 = safe[k].x;
					int y3 = safe[k].y;
					map[x3][y3] = 1;

					for (boolean[] v: visited) Arrays.fill(v, false);
					tmpArea = safeArea;
					
					for (Pt p : start) {
//						visited[p.x][p.y] = true;
						dfs(start, p.x, p.y);
					}
					if (tmpArea > max) max = tmpArea;
//					System.out.println("tmp : "+tmpArea+
//							" max : "+max);
					
					map[x3][y3] = 0;
				}
				map[x2][y2] = 0;
			}
			map[x1][y1] = 0;
		}
		
		bw.write(String.valueOf(max-3));
		bw.write("\n");
		bw.flush(); bw.close();
	}
}
