package samsung_sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Sam_14503 {
	static int n, m;
	
	static boolean inside(int i, int j) {
		if (i >= 0 && i < n && j >= 0 && j <m) return true;
		else return false;
	}
	
	static int bfs(int curi, int curj, int curd, int[][] map) {
		int ni, nj, nd, i, cnt;
		boolean flag;
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		boolean[][] visit = new boolean[n][m];
//		int[][] visit = new int[n][m];
		
		Queue<Integer> qi = new LinkedList<Integer>();
		Queue<Integer> qj = new LinkedList<Integer>();
		Queue<Integer> qd = new LinkedList<Integer>();
		
		cnt = 1;
		qi.add(curi);
		qj.add(curj);
		qd.add(curd);
		
		while (!qi.isEmpty()) {
			curi = qi.poll(); curj = qj.poll(); curd = qd.poll();
			visit[curi][curj] = true;
			nd = curd;
			flag = false;
			
			for (i = 0; i < 4; i++) {
				nd = (--nd == -1)? 3 : nd;
				ni = curi + dr[nd];
				nj = curj + dc[nd];
				if (!inside(ni, nj)) continue;
				if (visit[ni][nj] || map[ni][nj] == 1) continue;
				
				cnt++;
				flag = true;
				qi.add(ni); qj.add(nj); qd.add(nd);
				break;
			}
			
			if (!flag) {
				// 청소할 공간 없당
				ni = curi - dr[curd];
				nj = curj - dc[curd];
				if (!inside(ni, nj) || map[ni][nj] == 1) continue;
				qi.add(ni); qj.add(nj); qd.add(curd); // cnt 증가 x
			}
		}
		
//		for (int[] a: visit) {
//			for (int v: a) System.out.printf("%2d ",v);
//			System.out.println();
//		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] s;
		int curi, curj, curd;
		int i, j;
		int[][] map;
		
		// 인풋
		s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		
		s = br.readLine().split(" ");
		curi = Integer.parseInt(s[0]);
		curj = Integer.parseInt(s[1]); // 떨어진 칸수는 (0, 0)부터
		curd = Integer.parseInt(s[2]); // 보고 있는 방향
		
		map = new int[n][m];
		for (i = 0; i < n; i++) {
			s = br.readLine().split(" ");
			for (j=0; j < m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		sb.append(bfs(curi, curj, curd, map));
		System.out.println(sb.toString());
	}
}
