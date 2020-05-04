package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 16985 Maze

public class BOJ_16985_Maaaaaaaze_2 {
	static final int n = 5;
	static final int MAX = (int)1e4;
	static int min;
	static int[] turn;  // 조합
	static int[] perm;  // 순열
	static boolean[] pvis;  // 순열 방문 확인
	static int[][][] map;
	static int[][][] newmap;
	static boolean[][][] visit;  // bfs용
	static int[][] dir = {{1, 0, 0}, {0, 1, 0}, {-1, 0, 0}, 
							{0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
	
	static int[][] turn(int z, int dir) {
		int[][] res = new int[n][n];
		int op = n-1;
		
		switch(dir) {
			case 1: // 90
				for (int i=0; i<5; i++) {
					for (int j=0; j<5; j++) {
						res[op-j][i] = map[z][i][j];
					}
				}
				break;
			case 2: // 180
				for (int i=0; i<5; i++) {
					for (int j=0; j<5; j++) {
						res[op-i][op-j] = map[z][i][j];
					}
				}
				break;
			case 3:
				for (int i=0; i<5; i++) {
					for (int j=0; j<5; j++) {
						res[j][op-i] = map[z][i][j];
					}
				}
				break;
			default:
				for (int i=0; i<5; i++) {
					for (int j=0; j<5; j++) {
						res[i][j] = map[z][i][j];
					}
				}
				break;
		}
		return res;
	}
	
	static void cdfs(int depth) {  // 조합
		if (min == 12) return;
		
		if (depth == n) {
			Arrays.fill(pvis, false);
			pdfs(0);
			return;
		}

		for (int i=0; i<4; i++) {
			turn[depth] = i;
			cdfs(depth+1);
		}
	}
	
	static void pdfs(int depth) {  // 순열
		if (min == 12) return;
		
		if (depth == n) {
			for (int i=0; i<n; i++) newmap[perm[i]] = turn(i, turn[i]);
			for (boolean[][] vv: visit) {
				for (boolean[] v: vv) Arrays.fill(v, false);
			}
			
			if (newmap[0][0][0] == 1 && newmap[4][4][4] == 1) {
				go();  // bfs
			}
			return;
		}

		for (int i=0; i<n; i++) {
			if (pvis[i]) continue;
			perm[depth] = i;
			pvis[i] = true;
			pdfs(depth+1);
			pvis[i] = false;
		}
	}
	
	static void go() {
		// dfs: 가장 긴 루트로 가버리면 다른 길을 막게됨
		int x, y, z;
		int nx, ny, nz;
		int depth, size;
		
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		Queue<Integer> qz = new LinkedList<Integer>();
		
		x = y = z = 0;
		depth = 0;
		qx.add(x); qy.add(y); qz.add(z);
		
		while (!qx.isEmpty()) {
			size = qx.size();
			for (int s=0; s<size; s++) {
				x = qx.poll(); y = qy.poll(); z = qz.poll();
				
				if (x == n-1 && y == n-1 && z == n-1) {
					if (min > depth) {
						min = depth;
//						System.out.println("순열");
//						for (int dd:perm) System.out.print(dd+" ");
//						System.out.println();
					}
					return;
				}
				
				if (depth >= min) return;
				
				for (int d=0; d<6; d++) {
					nx = x + dir[d][0];
					ny = y + dir[d][1];
					nz = z + dir[d][2];
					
					if (nx >= 0 && ny >= 0 && nz >= 0 && nx < n && ny < n && nz < n) {
						if (newmap[nz][nx][ny] == 1 && !visit[nz][nx][ny]) {
							qx.add(nx); qy.add(ny); qz.add(nz);
							visit[nz][nx][ny] = true;
						}
					}
				}
			}
			depth++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] s;
		
		turn = new int[n];
		perm = new int[n];
		pvis = new boolean[n];
		map = new int[n][n][n];
		newmap = new int[n][n][n];
		visit = new boolean[n][n][n];
		
		for (int z=0; z<5; z++) {
			for (int i=0; i<5; i++) {
				s = br.readLine().split(" ");
				for (int j=0; j<5; j++) {
					map[z][i][j] = Integer.parseInt(s[j]);
				}
			}
		}
		
		min = MAX;
		cdfs(0);
		
		if (min == MAX) min = -1;
		sb.append(min);
		System.out.println(sb.toString());
	}
}
