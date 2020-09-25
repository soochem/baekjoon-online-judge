// 구슬을 넣는 횟수는 최소여야한다. break ㄴㄴ
// 10을 넘는 경우에는 -1로 처리
package samsung_sample;

import java.util.*;
import java.io.*;

public class Sam_13460_5 {
	public static int w, h;
	public static int[][] map;
	public static int[][][][] pass;
	public static int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	public static class Goosle{
		public int rx, ry, bx, by, cnt;
		public boolean die;
		Goosle () {};
		Goosle (int rx, int ry, boolean die) {
			this.rx = rx;
			this.ry = ry;
			this.die = die;
		}
		Goosle (int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		map = new int [h+1][w+1];
		pass = new int [h+1][w+1][h+1][w+1]; // 2차원은 1로 바꾸는 게 이상
		int rx, ry, bx, by; rx = ry = bx = by = 0;
		List<Integer> ans = new ArrayList<Integer>();
		
		for (int i=0; i<h; i++) {
			String s = br.readLine();
			for (int j=0; j<w; j++) {
				char c = s.charAt(j);
				if (c == '#') {
					map[i][j] = 1;
				}if (c == 'R') {
					rx = i; ry = j;
				}if (c == 'B') {
					bx = i; by = j;
				}if (c == 'O') {
					map[i][j] = -1;
				}
			}
		}
		
		Queue<Goosle> q = new LinkedList<Goosle>();
		q.offer(new Goosle(rx, ry, bx, by, 0));
		pass[rx][ry][bx][by] = 1;
		boolean hasans = false;
		
		while (!q.isEmpty()) {
			Goosle g = q.poll();
			
			for (int j=0; j<4; j++) {
				rx = g.rx; ry = g.ry; bx = g.bx; by = g.by;
				boolean rfirst = true;
				if (j==0 && ry < by) rfirst = false; // 오
				if (j==1 && ry > by) rfirst = false; // 왼
				if (j==2 && rx < bx) rfirst = false; // 아래
				if (j==3 && rx > bx) rfirst = false; // 위
				
				Goosle r = new Goosle(); Goosle b = new Goosle();
				if (rfirst) {
					r = move(rx, ry, 0, 0, j);
					if (r.die) b = move(bx, by, 0, 0, j);
					else b = move(bx, by, r.rx, r.ry, j);
				}
				else {
					b = move(bx, by, 0, 0, j);
					if (b.die) r = move(rx, ry, 0, 0, j);
					else r = move(rx, ry, b.rx, b.ry, j);
				}
				
				rx = r.rx; ry = r.ry; bx = b.rx; by = b.ry;
				// b가 빠지면 노답
				if (map[bx][by] == -1) {
					continue;
				}
				// b는 안빠지고 r은 빠진 경우
				if (map[rx][ry] == -1) {
					ans.add(++g.cnt);
					hasans = true;
					continue;
				}
				// b도 r도 안빠진 해피한 경우
				if (pass[rx][ry][bx][by] == 0) {
					pass[rx][ry][bx][by] = 1;
					q.offer(new Goosle (rx, ry, bx, by, g.cnt+1));
				}
			}
		}
		
		// 답 판별하기 : 답 있는 경우 hasans = true, 답이 리스트에 저장되어 있다..
		int min = 11;
		if (hasans) {
			for (int i : ans) {
				if (i != 0 && i < min) min = i;  // 최소값 찾기
			}
			if (min > 10) min = -1; // 혹시 10번 넘게 시도?
		}
		else min = -1;  // B만 빠졌거나 둘다 빠졌거나
		System.out.println(min);
	}
	
	private static Goosle move (int x, int y, int curx, int cury, int j) {
		boolean die = false;
		while (true) {
			x += dir[j][0];
			y += dir[j][1];
			if (map[x][y] == -1) {
				die = true;
				break;
			}
			if (map[x][y] == 1 || (curx == x && cury == y)) {
				x -= dir[j][0];
				y -= dir[j][1];
				break;
			}
		}
		Goosle ans = new Goosle(x, y, die);
		return ans;
	}
}
