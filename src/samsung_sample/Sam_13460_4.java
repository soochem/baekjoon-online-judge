package samsung_sample;
import java.util.*;
import java.io.*;

public class Sam_13460_4 {
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
		int goalx = 0; int goaly = 0;
		int cnt = 0;
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
					goalx = i; goaly = j;
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
			rx = g.rx; ry = g.ry; bx = g.bx; by = g.by;
			if (goalx == bx && goaly == by) {
				continue;
			}
			if (goalx == rx && goaly == ry) {
				ans.add(g.cnt);
				hasans = true;
				continue;
			}
			
			for (int j=0; j<4; j++) {
				boolean rfirst = true;
				rx = g.rx; ry = g.ry; bx = g.bx; by = g.by;
				if (j==0 && ry < by) rfirst = false; // 오
				if (j==1 && ry > by) rfirst = false; // 왼
				if (j==2 && rx < bx) rfirst = false; // 아래
				if (j==3 && rx > bx) rfirst = false; // 위
				
				// 초기화 매우 중요
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
				if (pass[rx][ry][bx][by] == 0) {
					pass[rx][ry][bx][by] = 1;
					q.offer(new Goosle (rx, ry, bx, by, g.cnt+1));
				}
			}
		}
		
		if (hasans) {
			int min = 11;
			for (int i : ans) {
				if (i != 0 && i < min) min = i;
			}
			cnt = min;
			if (cnt > 10) cnt = -1;
		}
		else cnt = -1;
		System.out.println(cnt);
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
