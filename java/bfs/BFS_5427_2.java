package bfs;

import java.util.*;
import java.io.*;

public class BFS_5427_2 {
	public static int w, h;
	
	public static class Vtx{
		public int x, y;
		public Vtx() {};
		public Vtx (int x, int y) {
			this.x = x; // 행
			this.y = y; // 열
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ansx, ansy;
		int test = Integer.parseInt(br.readLine());
		
		while (--test >= 0) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			int[][] map = new int [h+1][w+1];
			int[][] pass = new int [h+1][w+1];
			Queue<Vtx> q = new LinkedList<Vtx>();
			Vtx start = new Vtx();
			
			for (int i=0; i<h; i++) {
				String s = br.readLine();
				for (int j=0; j<w; j++) {
					char c = s.charAt(j);
					if (c == '#') {
						map[i][j] = 1;
					}
					if (c == '*') {
						map[i][j] = -1;
						q.offer(new Vtx(i, j));
					}
					if (c == '@') {
						pass[i][j] = 1;
						start = new Vtx(i, j);
					}
 				}
			}
			q.offer(start);
			
			int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
			boolean flag = true;
			ansx = ansy = -1;
			
			while (!q.isEmpty() && flag) {
				
				int qsize = q.size();
				while (--qsize >= 0) {
					Vtx cur = q.poll();
					System.out.println(cur.x+" "+cur.y);
					if (map[cur.x][cur.y] == -1) {
						for (int i=0; i<4; i++) {
							Vtx next = new Vtx(cur.x+dir[i][0], cur.y+dir[i][1]); 
							if (inside(next.x, next.y)) {
								if (map[next.x][next.y] == 0) {
									map[next.x][next.y] = -1;
									q.offer(next);
								}
							}
						}
						System.out.println("불이야");
						mapview(map);
					} else {
						if (cur.x==0 || cur.y==0 || cur.x==h-1 || cur.y==w-1) {
							ansx = cur.x; ansy = cur.y;
							flag = false;
						}
						
						for (int i=0; i<4; i++) {
							Vtx next = new Vtx(cur.x+dir[i][0], cur.y+dir[i][1]); 
							if (inside(next.x, next.y)) {
								if (map[next.x][next.y] == 0 &&  pass[next.x][next.y] == 0) {
									pass[next.x][next.y] = pass[cur.x][cur.y]+1;
									q.offer(next);
								}
							}
						}
						System.out.println("ㅌㅌ");
						mapview(pass);
					}
					
				}
			}
			
			if (flag) System.out.println("IMPOSSIBLE");
			else System.out.println(pass[ansx][ansy]);
		}
		
	}
	private static boolean inside(int x, int y) {
		if (x>=0 && y>=0 && x<h && y<w) return true;
		else return false;
	}
	private static void mapview(int[][] map) {
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
