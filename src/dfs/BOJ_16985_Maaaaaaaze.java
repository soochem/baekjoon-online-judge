package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 16985 Maze

public class BOJ_16985_Maaaaaaaze {
	static final int n = 5;
	static final int MAX = (int)1e8;
	static int min;
	static int[] perm;
	static boolean[] pvis;
	static int[][][] map;
	static int[][][] newmap;  // 메모리??
	static boolean[][][] visit;
	static int[][] dir = {{1, 0, 0}, {0, 1, 0}, {-1, 0, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
	
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
	
	static void dfs(int depth) {  // 조합
		if (depth == n) {
			Arrays.fill(pvis, false);
			pdfs(depth);
			return;
		}

		for (int i=0; i<4; i++) {
			perm[depth] = i;
			dfs(depth+1);
		}
	}
	
	static void pdfs(int depth) {  // 순열
		if (depth == n*2) {			
			for (int i=0; i<n; i++) newmap[perm[5+i]] = turn(i, perm[i]);
			for (boolean[][] vv: visit) {
				for (boolean[] v: vv) Arrays.fill(v, false);
			}			
			
//			for (int i:perm) System.out.print(i+" ");
//			System.out.println();
			
			if (newmap[0][0][0] == 1 && newmap[4][4][4] == 1) {
				visit[0][0][0] = true;
				go(0, 0, 0, 0);
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
	
	static void go(int z, int x, int y, int depth) {
		// 가장 긴 루트로 가버리면 다른 길을 막게됨
//		System.out.println(z+" "+x+" "+y+" "+depth);
		
//		if (depth >= 3) return;
		if (x == n-1 && y == n-1 && z == n-1) {
			if (min > depth) {
				min = depth;
				
//				System.out.println("순열");
//				for (int dd:perm) System.out.print(dd+" ");
//				System.out.println();
//
//				for (int zz=0; zz<5; zz++) {
//					System.out.println(perm[5+zz]+" "+ perm[zz]);
//					for (int i=0; i<5; i++) {
//						for (int j=0; j<5; j++) {
//							System.out.print(newmap[zz][i][j]+" ");
//						}
//						System.out.println();
//					}
//				}System.out.println();
			}
			return;
		}
		
		if (depth >= min || depth == 12) return;
		
		int nx, ny, nz;  // 아래가는 게 먼저!
		
		for (int d=0; d<6; d++) {
			nx = x + dir[d][0];
			ny = y + dir[d][1];
			nz = z + dir[d][2];
			
			if (nx >= 0 && ny >= 0 && nz >= 0 && nx < n && ny < n && nz < n) {
				if (newmap[nz][nx][ny] == 1 && !visit[nz][nx][ny]) {
					visit[nz][nx][ny] = true;
//					System.out.println(z+" "+x+" "+y);
//					for (int zz=0; zz<5; zz++) {
//						System.out.println(perm[5+zz]+" "+ perm[zz]);
//						for (int i=0; i<5; i++) {
//							for (int j=0; j<5; j++) {
//								System.out.print(visit[zz][i][j]+" ");
//							}
//							System.out.println();
//						}
//					}System.out.println();
					go(nz, nx, ny, depth+1);
					visit[nz][nx][ny] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] s;
		
		perm = new int[n*2];
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
		dfs(0);
		
		if (min == MAX) min = -1;
		sb.append(min);
		System.out.println(sb.toString());
	}
}
