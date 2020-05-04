// BFS Depth 이용해보기

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_M7576_2 {
	public static int w, h;
	public static int[][] map; 
	public static int[][] pass;
	
	public static class Vert {
		public int x, y;
		public Vert() {};
 		public Vert(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		w = Integer.parseInt(s[0]);
		h = Integer.parseInt(s[1]);
		
		map = new int[h][w];
		pass = new int[h][w];
		Queue<Vert> route = new LinkedList<Vert>();
		int cnt0 = 0;
		
		for (int i=0; i<h; i++) {
			String[] s2 = br.readLine().split(" ");
			for (int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(s2[j]);
				if (map[i][j] == 1) {
					route.offer(new Vert(i, j));
				}
				if (map[i][j] == 0) cnt0++;
			}
		}
		
		int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
		int cnt = 0;  // 날짜수!!
		int day = 0;
		int x,y,next_x,next_y;
		
		// 레벨 이용하기
		// 최소, 최단거리 - BFS
		
		while (!route.isEmpty()) {
			int qsize = route.size();
			
			for (int j=0; j<qsize; j++) {
				Vert cur = route.poll();
				x = cur.x;
				y = cur.y;
				cnt = pass[x][y]+1;
				
				for (int i=0; i<4; i++) {
					next_x = x+dir[i][0];
					next_y = y+dir[i][1];
					
					if (inside(next_x, next_y)) {
						if (pass[next_x][next_y] == 0 && map[next_x][next_y] == 0) {
							pass[next_x][next_y] = cnt;
							route.offer(new Vert(next_x, next_y));
							cnt0--;
						}
					}
				}
			}
			day++;
		}
		
		if (cnt0 == 0) System.out.println(--cnt);
		else System.out.println(-1);  // 0의 개수 != 탐색한 숫자 : 답 -1
		
		System.out.println(--day);
 	}
	private static boolean inside (int x, int y) {
		if (x>=0 && y>=0 && x<h && y<w) return true;
		else return false;
	}
}
